package ru.bestaford.ariovale.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.LoginForm;
import ru.bestaford.ariovale.form.RegistrationForm;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.UtilsService;

import javax.inject.Inject;

public final class AuthenticationTask extends AsyncTask {

    public final Player player;
    public final String name;

    public Account account;
    public boolean registered;
    public boolean loggedIn;
    public boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private UtilsService utilsService;
    @Inject private FormService formService;
    @Inject private AuthenticationService authenticationService;

    public AuthenticationTask(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            account = session.get(Account.class, name);
            registered = account != null;
            if (registered) {
                loggedIn = player.getUniqueId().equals(account.getUUID());
            } else {
                loggedIn = false;
            }
            success = true;
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            if (registered) {
                if (loggedIn) {
                    authenticationService.completeLogin(player, account, false);
                } else {
                    formService.sendForm(new LoginForm(account), player);
                }
            } else {
                account = new Account();
                account.setName(name);
                formService.sendForm(new RegistrationForm(account), player);
            }
        } else {
            utilsService.closeWithError(player);
        }
    }
}