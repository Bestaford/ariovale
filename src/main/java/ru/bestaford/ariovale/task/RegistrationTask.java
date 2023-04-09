package ru.bestaford.ariovale.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bestaford.ariovale.entity.Account;

import javax.inject.Inject;

public final class RegistrationTask extends AsyncTask implements Task {

    private final SessionFactory sessionFactory;
    private Player player;
    private Account account;

    @Inject
    public RegistrationTask(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void onRun() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            session.persist(account);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onCompletion(Server server) {

    }
}