package ru.bestaford.ariovale.task.authentication;

import at.favre.lib.crypto.bcrypt.BCrypt;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.entity.LoginHistory;
import ru.bestaford.ariovale.entity.PlayerState;
import ru.bestaford.ariovale.manager.AuthenticationManager;
import ru.bestaford.ariovale.manager.UtilsManager;
import ru.bestaford.ariovale.util.Strings;

import java.time.LocalDateTime;

@Log4j2
public final class RegistrationTask extends AsyncTask {

    public final Player player;
    public final Account account;

    public boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private AuthenticationManager authenticationManager;
    @Inject private UtilsManager utilsManager;

    public RegistrationTask(Player player, Account account) {
        Preconditions.checkArgument(player != null && account != null);
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
            authenticationManager.completeRegistration(player, account);
        } else {
            utilsManager.closeWithError(player);
        }
    }
}