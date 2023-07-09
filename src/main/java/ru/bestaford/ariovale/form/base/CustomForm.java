package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;


public abstract class CustomForm implements Form {

    protected FormWindowCustom window;

    protected abstract void build(Player player);

    @Override
    public FormWindow getWindow(Player player) {
        window = new FormWindowCustom("");
        build(player);
        return window;
    }

    public abstract void handle(Player player, boolean wasClosed, FormResponseCustom response);
}