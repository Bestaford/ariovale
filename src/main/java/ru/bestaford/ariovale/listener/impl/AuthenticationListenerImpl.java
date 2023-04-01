package ru.bestaford.ariovale.listener.impl;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import ru.bestaford.ariovale.listener.AuthenticationListener;
import ru.bestaford.ariovale.service.AuthenticationService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class AuthenticationListenerImpl implements AuthenticationListener {

    private final AuthenticationService authenticationService;

    @Inject
    public AuthenticationListenerImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPreLogin(PlayerPreLoginEvent event) {
        if (!authenticationService.isValidSession(event.getPlayer())) {
            event.setKickMessage("not valid"); //TODO: change message
            event.setCancelled();
        }
    }

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        authenticationService.initialize(event.getPlayer());
    }

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLocallyInitialized(PlayerLocallyInitializedEvent event) {
        authenticationService.process(event.getPlayer());
    }
}