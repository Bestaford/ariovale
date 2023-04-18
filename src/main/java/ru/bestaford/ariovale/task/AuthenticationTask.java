package ru.bestaford.ariovale.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.RegistrationForm;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.UtilsService;

import javax.inject.Inject;

public final class AuthenticationTask extends AsyncTask {

    private final Player player;
    private final String name;

    private Account account;
    private boolean registered;
    private boolean logined;
    private boolean success;

    @Inject private SessionFactory sessionFactory;
    @Inject private UtilsService utilsService;
    @Inject private FormService formService;

    public AuthenticationTask(Player player, String name) {
        this.player = player;
        this.name = name;
    }

    @Override
    public void onRun() {
        try (Session session = sessionFactory.openSession()) {
            account = session.get(Account.class, name);
            registered = account != null;
            logined = false;
            success = true;
        } catch (Exception exception) {
            utilsService.throwError(player, exception);
        }
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            if (registered) {
                if (logined) {
                    player.sendMessage("logined");
                } else {
                    player.sendMessage("not logined");
                }
            } else {
                account = new Account();
                account.setName(name);
                formService.sendForm(new RegistrationForm(account), player);
            }
        } else {
            utilsService.throwError(player);
        }
    }
}