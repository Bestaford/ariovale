package ru.bestaford.ariovale.listener.impl;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import ru.bestaford.ariovale.form.CustomForm;
import ru.bestaford.ariovale.form.ModalForm;
import ru.bestaford.ariovale.form.SimpleForm;
import ru.bestaford.ariovale.listener.FormListener;

import javax.inject.Singleton;

@Singleton
public final class FormListenerImpl implements FormListener {

    @Override
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFormResponded(PlayerFormRespondedEvent event) {
        FormWindow window = event.getWindow();
        Player player = event.getPlayer();
        boolean wasClosed = event.wasClosed();
        FormResponse response = event.getResponse();
        if (window instanceof SimpleForm) {
            ((SimpleForm) window).handle(player, wasClosed, (FormResponseSimple) response);
        } else if (window instanceof ModalForm) {
            ((ModalForm) window).handle(player, wasClosed, (FormResponseModal) response);
        } else if (window instanceof CustomForm) {
            ((CustomForm) window).handle(player, wasClosed, (FormResponseCustom) response);
        }
    }
}