package ru.bestaford.ariovale.task.authentication;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.SelectionQuery;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.AuthenticationForm;
import ru.bestaford.ariovale.manager.AuthenticationManager;
import ru.bestaford.ariovale.manager.FormManager;
import ru.bestaford.ariovale.manager.UtilsManager;

import javax.inject.Inject;

public final class IdentificationTask extends AsyncTask {

    public final Player player;

    public String name;
    public boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private UtilsManager utilsManager;
    @Inject private AuthenticationManager authenticationManager;
    @Inject private FormManager formManager;

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
            AuthenticationForm authenticationForm = new AuthenticationForm();
            if (name == null) {
                formManager.sendForm(authenticationForm, player);
            } else {
                formManager.sendForm(authenticationForm, player, true);
                authenticationManager.authenticate(player, name);
            }
        } else {
            utilsManager.closeWithError(player);
        }
    }
}