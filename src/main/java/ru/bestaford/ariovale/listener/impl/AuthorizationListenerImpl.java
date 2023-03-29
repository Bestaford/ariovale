package ru.bestaford.ariovale.listener.impl;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import ru.bestaford.ariovale.listener.AuthorizationListener;
import ru.bestaford.ariovale.service.AuthorizationService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class AuthorizationListenerImpl implements AuthorizationListener {

    private final AuthorizationService authorizationService;

    @Inject
    public AuthorizationListenerImpl(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPreLogin(PlayerPreLoginEvent event) {
        if (!authorizationService.isValidSession(event.getPlayer())) {
            event.setKickMessage("not valid"); //TODO: change message
            event.setCancelled();
        }
    }

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        authorizationService.initialize(event.getPlayer());
    }

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLocallyInitialized(PlayerLocallyInitializedEvent event) {
        authorizationService.process(event.getPlayer());
    }
}