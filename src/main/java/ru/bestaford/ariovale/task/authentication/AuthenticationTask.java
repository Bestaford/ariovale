package ru.bestaford.ariovale.task.authentication;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.entity.LoginHistory;
import ru.bestaford.ariovale.form.LoginForm;
import ru.bestaford.ariovale.form.RegistrationForm;
import ru.bestaford.ariovale.manager.AuthenticationManager;
import ru.bestaford.ariovale.manager.FormManager;
import ru.bestaford.ariovale.manager.UtilsManager;
import ru.bestaford.ariovale.util.Strings;

@Log4j2
public final class AuthenticationTask extends AsyncTask {

    public final Player player;
    public final String name;

    public Account account;
    public boolean registered;
    public boolean loggedIn;
    public boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private UtilsManager utilsManager;
    @Inject private FormManager formManager;
    @Inject private AuthenticationManager authenticationManager;

    public AuthenticationTask(Player player, String name) {
        Preconditions.checkArgument(player != null && name != null);
        this.player = player;
        this.name = name;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            account = session.bySimpleNaturalId(Account.class).load(name);
            registered = account != null;
            if (registered) {
                loggedIn = player.getUniqueId().equals(account.getUniqueId());
                if (loggedIn) {
                    Transaction transaction = session.beginTransaction();
                    try {
                        account.getLoginHistory().add(new LoginHistory(player, account));
                        account = session.merge(account);
                        transaction.commit();
                    } catch (Exception exception) {
                        transaction.rollback();
                        log.error(Strings.ERROR_TRANSACTION, exception);
                        return;
                    }
                }
            } else {
                loggedIn = false;
            }
            success = true;
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            if (registered) {
                if (loggedIn) {
                    authenticationManager.completeLogin(player, account, false);
                } else {
                    formManager.sendForm(new LoginForm(account), player);
                }
            } else {
                account = new Account();
                account.setName(name);
                formManager.sendForm(new RegistrationForm(account), player);
            }
        } else {
            utilsManager.closeWithError(player);
        }
    }
}