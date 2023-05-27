package ru.bestaford.ariovale.task.authentication;

import at.favre.lib.crypto.bcrypt.BCrypt;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.entity.LoginHistory;
import ru.bestaford.ariovale.entity.PlayerState;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.UtilsService;
import ru.bestaford.ariovale.util.Strings;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Objects;

@Log4j2
public final class RegistrationTask extends AsyncTask {

    public final Player player;
    public final Account account;

    public boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private AuthenticationService authenticationService;
    @Inject private UtilsService utilsService;

    public RegistrationTask(Player player, Account account) {
        this.player = Objects.requireNonNull(player);
        this.account = Objects.requireNonNull(account);
    }

    @Override
    public void onRun() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            String hashedPassword = BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, account.getPassword().toCharArray());
            account.setPassword(hashedPassword);
            account.setRegistrationDate(LocalDateTime.now());
            account.setUniqueId(player.getUniqueId());
            account.setPlayerState(new PlayerState(player));
            account.getLoginHistory().add(new LoginHistory(player, account));
            session.persist(account);
            transaction.commit();
            success = true;
        } catch (Exception exception) {
            transaction.rollback();
            log.error(Strings.ERROR_TRANSACTION, exception);
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