package ru.bestaford.ariovale.task;

import at.favre.lib.crypto.bcrypt.BCrypt;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.service.GameService;

import javax.inject.Inject;

public final class LoginTask extends AsyncTask {

    private final Player player;
    private final Account account;
    private final String password;

    private boolean verified;
    private boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private GameService gameService;

    public LoginTask(Player player, Account account, String password) {
        this.player = player;
        this.account = account;
        this.password = password;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            BCrypt.Verifyer verifyer = BCrypt.verifyer();
            BCrypt.Result result = verifyer.verify(password.toCharArray(), session.getReference(account).getPassword().toCharArray());
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