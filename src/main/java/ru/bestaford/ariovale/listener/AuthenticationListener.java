package ru.bestaford.ariovale.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import ru.bestaford.ariovale.service.AuthenticationService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class AuthenticationListener implements Listener {

    private final AuthenticationService authenticationService;

    @Inject
    public AuthenticationListener(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPreLogin(PlayerPreLoginEvent event) {
        if (!authenticationService.isValidSession(event.getPlayer())) {
            event.setKickMessage("not valid"); //TODO: change message
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        authenticationService.initialize(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLocallyInitialized(PlayerLocallyInitializedEvent event) {
        authenticationService.process(event.getPlayer());
    }
}