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

public final class AuthenticationTask extends AsyncTask implements Task {

    private final SessionFactory sessionFactory;
    private final FormService formService;
    private Player player;
    private String name;
    private boolean isRegistered;

    @Inject
    public AuthenticationTask(SessionFactory sessionFactory, FormService formService) {
        this.sessionFactory = sessionFactory;
        this.formService = formService;
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
            isRegistered = session.createSelectionQuery("select a.name from Account a where a.name = :name")
                    .setParameter("name", name)
                    .uniqueResult() != null;
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (isRegistered) {

        } else {
            Account account = new Account();
            account.setName(name);
            RegistrationForm registrationForm = formService.createForm(RegistrationForm.class);
            registrationForm.setAccount(account);
            formService.sendForm(registrationForm, player);
        }
    }
}