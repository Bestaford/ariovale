package ru.bestaford.ariovale.task;

import at.favre.lib.crypto.bcrypt.BCrypt;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.UtilsService;

import javax.inject.Inject;

public final class RegistrationTask extends AsyncTask implements Task {

    private final SessionFactory sessionFactory;
    private final UtilsService utilsService;
    private final AuthenticationService authenticationService;
    private Player player;
    private Account account;
    private boolean success;

    @Inject
    public RegistrationTask(SessionFactory sessionFactory, UtilsService utilsService, AuthenticationService authenticationService) {
        this.sessionFactory = sessionFactory;
        this.utilsService = utilsService;
        this.authenticationService = authenticationService;
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
            String hashedPassword = BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, account.getPassword().toCharArray());
            account.setPassword(hashedPassword);
            session.persist(account);
            transaction.commit();
            success = true;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            authenticationService.completeRegistration(player, account);
        } else {
            utilsService.hardError(player);
        }
    }
}