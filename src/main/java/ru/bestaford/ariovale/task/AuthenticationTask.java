package ru.bestaford.ariovale.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.bestaford.ariovale.entity.Account;

import javax.inject.Inject;

public final class AuthenticationTask extends AsyncTask implements Task {

    private final SessionFactory sessionFactory;
    private Player player;
    private String name;
    private boolean isRegistered;

    @Inject
    public AuthenticationTask(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            isRegistered = session.get(Account.class, name) != null;
        }
    }

    @Override
    public void onCompletion(Server server) {
        player.sendMessage(String.valueOf(isRegistered));
    }
}