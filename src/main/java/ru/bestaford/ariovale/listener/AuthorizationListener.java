package ru.bestaford.ariovale.listener;

import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import cn.nukkit.event.player.PlayerPreLoginEvent;

public interface AuthorizationListener extends Listener {

    void onPlayerPreLogin(PlayerPreLoginEvent event);

    void onPlayerJoin(PlayerJoinEvent event);

    void onPlayerLocallyInitialized(PlayerLocallyInitializedEvent event);

}