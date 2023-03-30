package ru.bestaford.ariovale.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.bestaford.ariovale.entity.Account;

import javax.inject.Inject;

public final class AuthorizationTask extends AsyncTask {

    private final transient Player player;
    private boolean isRegistered;
    private boolean isLogined;
    private boolean success;

    @Inject
    private SessionFactory sessionFactory;

    public AuthorizationTask(Player player) {
        this.player = player;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            isRegistered = session.get(Account.class, "") != null;
        }
        success = true;
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            if (isRegistered) {
                if (isLogined) {
                    //TODO: complete login
                } else {
                    //TODO: send login form
                }
            } else {
                //TODO: send registration form
            }
        }
    }
}