package ru.bestaford.ariovale.listener.impl;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import ru.bestaford.ariovale.listener.FormListener;
import ru.bestaford.ariovale.service.FormService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class FormListenerImpl implements FormListener {

    private final FormService formService;

    @Inject
    public FormListenerImpl(FormService formService) {
        this.formService = formService;
    }

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFormResponded(PlayerFormRespondedEvent event) {
        formService.handleResponse(event.getWindow(), event.getPlayer(), event.wasClosed(), event.getResponse());
    }
}