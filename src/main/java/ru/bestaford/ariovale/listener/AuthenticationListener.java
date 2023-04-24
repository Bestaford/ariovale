package ru.bestaford.ariovale.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import ru.bestaford.ariovale.service.AuthenticationService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class AuthenticationListener implements Listener {

    @Inject private AuthenticationService authenticationService;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLocallyInitialized(PlayerLocallyInitializedEvent event) {
        Player player = event.getPlayer();
        authenticationService.initialize(player);
        authenticationService.process(player);
    }
}