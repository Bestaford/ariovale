package ru.bestaford.ariovale.task;

import at.favre.lib.crypto.bcrypt.BCrypt;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.bestaford.ariovale.form.LoginForm;
import ru.bestaford.ariovale.service.GameService;

import javax.inject.Inject;

public final class LoginTask extends AsyncTask {

    public final Player player;
    public final LoginForm loginForm;

    public boolean verified;
    public boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private GameService gameService;

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
            player.sendMessage(String.valueOf(verified));
        } else {
            gameService.closeWithError(player);
        }
    }
}