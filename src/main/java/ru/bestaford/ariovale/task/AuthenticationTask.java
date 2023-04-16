package ru.bestaford.ariovale.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.LoginForm;
import ru.bestaford.ariovale.form.RegistrationForm;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.UtilsService;

import javax.inject.Inject;

public final class AuthenticationTask extends AsyncTask implements Task {

    private final SessionFactory sessionFactory;
    private final FormService formService;
    private final UtilsService utilsService;
    private Player player;
    private String name;
    private Account account;
    private boolean isRegistered;
    private boolean isLoggedIn;
    private boolean success;

    @Inject
    public AuthenticationTask(SessionFactory sessionFactory, FormService formService, UtilsService utilsService) {
        this.sessionFactory = sessionFactory;
        this.formService = formService;
        this.utilsService = utilsService;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            account = session.get(Account.class, name);
            isRegistered = account != null;
            isLoggedIn = false;
            success = true;
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            if (isRegistered) {
                if (isLoggedIn) {
                    player.sendMessage("logged in");
                } else {
                    LoginForm loginForm = formService.createForm(LoginForm.class);
                    loginForm.setAccount(account);
                    formService.sendForm(loginForm, player);
                }
            } else {
                account = new Account();
                account.setName(name);
                RegistrationForm registrationForm = formService.createForm(RegistrationForm.class);
                registrationForm.setAccount(account);
                formService.sendForm(registrationForm, player);
            }
        } else {
            utilsService.hardError(player);
        }
    }
}