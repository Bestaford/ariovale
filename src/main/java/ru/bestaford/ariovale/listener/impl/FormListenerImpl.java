package ru.bestaford.ariovale.listener.impl;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import ru.bestaford.ariovale.listener.FormListener;

import javax.inject.Singleton;

@Singleton
public final class FormListenerImpl implements FormListener {

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFormResponded(PlayerFormRespondedEvent event) {

    }
}