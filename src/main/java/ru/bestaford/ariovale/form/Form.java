package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindow;

public interface Form {

    void build(Player targetPlayer);

    default void send(Player targetPlayer) {
        build(targetPlayer);
        targetPlayer.showFormWindow((FormWindow) this);
    }
}