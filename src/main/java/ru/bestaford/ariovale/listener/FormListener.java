package ru.bestaford.ariovale.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import ru.bestaford.ariovale.manager.FormManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Stack;

@Singleton
public final class FormListener implements Listener {

    @Inject private FormManager formManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        formManager.formStackMap.put(event.getPlayer(), new Stack<>());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFormResponded(PlayerFormRespondedEvent event) {
        formManager.handleResponse(event.getWindow(), event.getPlayer(), event.wasClosed(), event.getResponse());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        formManager.formStackMap.remove(event.getPlayer());
    }
}