package ru.bestaford.ariovale.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.bestaford.ariovale.service.AuthorizationService;

@Singleton
public final class AuthorizationListener implements Listener {

    private final AuthorizationService authorizationService;

    @Inject
    public AuthorizationListener(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPreLogin(PlayerPreLoginEvent event) {
        if (!authorizationService.isValidSession(event.getPlayer())) {
            //TODO: implement
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        authorizationService.initialize(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLocallyInitialized(PlayerLocallyInitializedEvent event) {
        authorizationService.process(event.getPlayer());
    }
}