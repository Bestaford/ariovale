package ru.bestaford.ariovale.task;

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
            session.refresh(account);
            success = true;
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            player.sendMessage("login");
        } else {
            gameService.closeWithError(player);
        }
    }
}