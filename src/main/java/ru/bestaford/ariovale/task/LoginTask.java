package ru.bestaford.ariovale.task;

import at.favre.lib.crypto.bcrypt.BCrypt;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.LoginForm;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;
import ru.bestaford.ariovale.service.UtilsService;
import ru.bestaford.ariovale.util.Strings;

import javax.inject.Inject;
import java.util.Date;

@Log4j2
public final class LoginTask extends AsyncTask {

    public final Player player;
    public final LoginForm loginForm;

    public Account account;
    public boolean verified;
    public boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private UtilsService utilsService;
    @Inject private FormService formService;
    @Inject private TranslationService translationService;
    @Inject private AuthenticationService authenticationService;

    public LoginTask(Player player, LoginForm loginForm) {
        this.player = player;
        this.loginForm = loginForm;
        account = loginForm.account;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            BCrypt.Verifyer verifyer = BCrypt.verifyer();
            BCrypt.Result result = verifyer.verify(loginForm.password.toCharArray(), session.getReference(account).getPassword().toCharArray());
            verified = result.verified;
            if (verified) {
                Transaction transaction = session.beginTransaction();
                try {
                    account.setLastDate(new Date());
                    account.setAddress(player.getAddress());
                    account.setUUID(player.getUniqueId());
                    account.setXUID(player.getLoginChainData().getXUID());
                    account = session.merge(account);
                    transaction.commit();
                } catch (Exception exception) {
                    transaction.rollback();
                    log.error("An error occurred during transaction", exception);
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
                formService.clearStack(player);
                authenticationService.completeLogin(player, account);
            } else {
                loginForm.password = null;
                loginForm.error = Strings.THEME_ERROR + translationService.getString(player, "login.form.input.error.incorrect");
                formService.sendForm(loginForm, player);
            }
        } else {
            utilsService.closeWithError(player);
        }
    }
}