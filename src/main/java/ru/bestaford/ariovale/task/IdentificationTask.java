package ru.bestaford.ariovale.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.AuthenticationForm;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.UtilsService;

import javax.inject.Inject;

public final class IdentificationTask extends AsyncTask {

    public final Player player;

    public String name;
    public boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private UtilsService utilsService;
    @Inject private AuthenticationService authenticationService;
    @Inject private FormService formService;

    public IdentificationTask(Player player) {
        this.player = player;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            SelectionQuery<Account> query = session.createSelectionQuery("select l.account from LoginHistory l where l.uniqueId=:uuid order by l.datetime desc", Account.class);
            query.setParameter("uuid", player.getUniqueId());
            query.setMaxResults(1);
            Account account = query.uniqueResult();
            if (account != null) {
                name = account.getName();
            }
            success = true;
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            if (name == null) {
                formService.sendForm(new AuthenticationForm(), player);
            } else {
                authenticationService.authenticate(player, name);
            }
        } else {
            utilsService.closeWithError(player);
        }
    }
}