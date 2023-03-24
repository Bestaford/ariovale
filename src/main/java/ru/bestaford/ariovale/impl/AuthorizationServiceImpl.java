package ru.bestaford.ariovale.impl;

import cn.nukkit.Player;
import com.google.inject.Singleton;
import ru.bestaford.ariovale.service.AuthorizationService;

@Singleton
public final class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public boolean isValidSession(Player player) {
        return false; //TODO: implement
    }

    @Override
    public void initialize(Player player) {
        player.sendMessage("initialize"); //TODO: implement
    }

    @Override
    public void process(Player player) {
        player.sendMessage("process"); //TODO: implement
    }
}