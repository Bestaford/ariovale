package ru.bestaford.ariovale.listener.impl;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerLocallyInitializedEvent;
import cn.nukkit.event.player.PlayerPreLoginEvent;
import ru.bestaford.ariovale.listener.AuthorizationListener;

import javax.inject.Singleton;

@Singleton
public final class AuthorizationListenerImpl implements AuthorizationListener {

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPreLogin(PlayerPreLoginEvent event) {

    }

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLocallyInitialized(PlayerLocallyInitializedEvent event) {

    }
}