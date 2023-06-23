package ru.bestaford.ariovale.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.bestaford.ariovale.service.FormService;

import java.util.Stack;

@Singleton
public final class FormListener implements Listener {

    @Inject private FormService formService;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        formService.stackMap.put(event.getPlayer(), new Stack<>());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFormResponded(PlayerFormRespondedEvent event) {
        formService.handleResponse(event.getWindow(), event.getPlayer(), event.wasClosed(), event.getResponse());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        formService.stackMap.remove(event.getPlayer());
    }
}