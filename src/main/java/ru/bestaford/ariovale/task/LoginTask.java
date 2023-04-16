package ru.bestaford.ariovale.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import ru.bestaford.ariovale.entity.Account;

public final class LoginTask extends AsyncTask implements Task {

    private Player player;
    private Account account;
    private boolean success;

    public LoginTask() {

    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void onRun() {
        success = true;
    }

    @Override
    public void onCompletion(Server server) {
        if (player.isOnline() && success) {
            player.sendMessage("login");
        }
    }
}