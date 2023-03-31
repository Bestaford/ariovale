package ru.bestaford.ariovale.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.RegistrationForm;
import ru.bestaford.ariovale.service.FormService;

import javax.inject.Inject;

public final class AuthorizationTask extends AsyncTask {

    private final Player player;
    private boolean isRegistered;

    @Inject
    private SessionFactory sessionFactory;

    @Inject
    private FormService formService;

    public AuthorizationTask(Player player) {
        this.player = player;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            isRegistered = session.get(Account.class, player.getName()) != null;
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (isRegistered) {
            //TODO: implement
        } else {
            formService.sendForm(RegistrationForm.class, player);
        }
    }
}