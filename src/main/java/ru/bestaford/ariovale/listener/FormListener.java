package ru.bestaford.ariovale.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.bestaford.ariovale.service.FormService;

@Singleton
public final class FormListener implements Listener {

    private final FormService formService;

    @Inject
    public FormListener(FormService formService) {
        this.formService = formService;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFormResponded(PlayerFormRespondedEvent event) {
        formService.handleResponse(event.getFormID(), event.getPlayer(), event.wasClosed(), event.getResponse());
    }
}