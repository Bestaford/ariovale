package ru.bestaford.ariovale.task;

import at.favre.lib.crypto.bcrypt.BCrypt;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.bestaford.ariovale.form.LoginForm;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;
import ru.bestaford.ariovale.service.UtilsService;
import ru.bestaford.ariovale.util.Strings;

import javax.inject.Inject;

public final class LoginTask extends AsyncTask {

    public final Player player;
    public final LoginForm loginForm;

    public boolean verified;
    public boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private UtilsService utilsService;
    @Inject private FormService formService;
    @Inject private TranslationService translationService;

    public LoginTask(Player player, LoginForm loginForm) {
        this.player = player;
        this.loginForm = loginForm;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            BCrypt.Verifyer verifyer = BCrypt.verifyer();
            BCrypt.Result result = verifyer.verify(loginForm.password.toCharArray(), session.getReference(loginForm.account).getPassword().toCharArray());
            verified = result.verified;
            success = true;
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            if (verified) {
                player.sendMessage("login");
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