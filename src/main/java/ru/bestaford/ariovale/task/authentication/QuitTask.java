package ru.bestaford.ariovale.task.authentication;

import cn.nukkit.Player;
import cn.nukkit.scheduler.AsyncTask;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.util.Strings;

@Log4j2
public final class QuitTask extends AsyncTask {

    public final Player player;
    public final String name;

    private @Inject SessionFactory sessionFactory;

    public QuitTask(Player player, String name) {
        Preconditions.checkArgument(player != null && name != null);
        this.player = player;
        this.name = name;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            Account account = session.bySimpleNaturalId(Account.class).load(name);
            if (account != null) {
                Transaction transaction = session.beginTransaction();
                try {
                    account.getPlayerState().save(player);
                    session.merge(account);
                    transaction.commit();
                } catch (Exception exception) {
                    transaction.rollback();
                    log.error(Strings.ERROR_TRANSACTION, exception);
                }
            }
        }
    }
}