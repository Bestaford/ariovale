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
    private final String name;
    private final String password;

    private boolean verified;
    private boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private GameService gameService;

    public LoginTask(Player player, String name, String password) {
        this.player = player;
        this.name = name;
        this.password = password;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            Account account = session.get(Account.class, name);
            verified = BCrypt.verifyer().verify(password.toCharArray(), account.getPassword().toCharArray()).verified;
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