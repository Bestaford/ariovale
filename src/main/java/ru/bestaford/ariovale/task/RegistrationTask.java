package ru.bestaford.ariovale.task;

import at.favre.lib.crypto.bcrypt.BCrypt;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.utils.MainLogger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.GameService;

import javax.inject.Inject;

public final class RegistrationTask extends AsyncTask {

    private final Player player;
    private final Account account;

    private boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private AuthenticationService authenticationService;
    @Inject private GameService gameService;
    @Inject private MainLogger logger;

    public RegistrationTask(Player player, Account account) {
        this.player = player;
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
        } catch (Exception exception) {
            transaction.rollback();
            logger.logException(exception);
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            authenticationService.completeRegistration(player, account);
        } else {
            gameService.closeWithError(player);
        }
    }
}