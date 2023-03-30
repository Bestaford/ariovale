package ru.bestaford.ariovale.task;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;

public final class AuthorizationTask extends AsyncTask {

    private final transient Player player;

    public AuthorizationTask(Player player) {
        this.player = player;
    }

    @Override
    public void onRun() {

    }

    @Override
    public void onCompletion(Server server) {

    }
}