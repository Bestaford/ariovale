package ru.bestaford.ariovale.task;

import at.favre.lib.crypto.bcrypt.BCrypt;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.UtilsService;
import ru.bestaford.ariovale.util.PermissionLevel;

import javax.inject.Inject;
import java.util.Date;

@Log4j2
public final class RegistrationTask extends AsyncTask {

    public final Player player;
    public final Account account;

    public boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private AuthenticationService authenticationService;
    @Inject private UtilsService utilsService;

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
            account.setPermissionLevel(PermissionLevel.PLAYER);
            account.setRegistrationDate(new Date());
            account.setLastDate(account.getRegistrationDate());
            account.setAddress(player.getAddress());
            account.setUUID(player.getUniqueId());
            account.setXUID(player.getLoginChainData().getXUID());
            session.persist(account);
            transaction.commit();
            success = true;
        } catch (Exception exception) {
            transaction.rollback();
            log.error("An error occurred during transaction", exception);
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            authenticationService.completeRegistration(player, account);
        } else {
            utilsService.closeWithError(player);
        }
    }
}