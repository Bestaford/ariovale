package ru.bestaford.ariovale.listener;

import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;

public interface FormListener extends Listener {

    void onPlayerFormResponded(PlayerFormRespondedEvent event);

}