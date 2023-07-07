package ru.bestaford.ariovale.task.authentication;

import at.favre.lib.crypto.bcrypt.BCrypt;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.entity.LoginHistory;
import ru.bestaford.ariovale.form.LoginForm;
import ru.bestaford.ariovale.manager.AuthenticationManager;
import ru.bestaford.ariovale.manager.FormManager;
import ru.bestaford.ariovale.manager.TranslationManager;
import ru.bestaford.ariovale.manager.UtilsManager;
import ru.bestaford.ariovale.util.Strings;

import java.util.Objects;

@Log4j2
public final class LoginTask extends AsyncTask {

    public final Player player;
    public final LoginForm loginForm;

    public Account account;
    public boolean verified;
    public boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private UtilsManager utilsManager;
    @Inject private FormManager formManager;
    @Inject private TranslationManager translationManager;
    @Inject private AuthenticationManager authenticationManager;

    public LoginTask(Player player, LoginForm loginForm) {
        this.player = Objects.requireNonNull(player);
        this.loginForm = Objects.requireNonNull(loginForm);
        account = loginForm.account;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            account = session.getReference(account);
            BCrypt.Verifyer verifyer = BCrypt.verifyer();
            BCrypt.Result result = verifyer.verify(loginForm.password.toCharArray(), account.getPassword().toCharArray());
            verified = result.verified;
            if (verified) {
                Transaction transaction = session.beginTransaction();
                try {
                    account.setUniqueId(player.getUniqueId());
                    account.getLoginHistory().add(new LoginHistory(player, account));
                    account = session.merge(account);
                    transaction.commit();
                } catch (Exception exception) {
                    transaction.rollback();
                    log.error(Strings.ERROR_TRANSACTION, exception);
                    return;
                }
            }
            success = true;
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            if (verified) {
                authenticationManager.completeLogin(player, account, false);
            } else {
                loginForm.password = null;
                loginForm.error = Strings.THEME_ERROR + translationManager.getString(player, "login.form.input.error.incorrect");
                formManager.sendForm(loginForm, player);
            }
        } else {
            utilsManager.closeWithError(player);
        }
    }
}
