package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowModal;


public abstract class ModalForm implements Form {

    protected FormWindowModal window;

    protected abstract void build(Player player);

    @Override
    public FormWindow getWindow(Player player) {
        window = new FormWindowModal("", "", "", "");
        build(player);
        return window;
    }

    public abstract void handle(Player player, boolean wasClosed, FormResponseModal response);
}