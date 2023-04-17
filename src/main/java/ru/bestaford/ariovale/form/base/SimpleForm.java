package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;

public abstract class SimpleForm implements Form {

    protected FormWindowSimple window;

    protected abstract void build(Player player);

    @Override
    public FormWindow getWindow(Player player) {
        window = new FormWindowSimple("", "");
        build(player);
        return window;
    }

    public abstract void handle(Player player, boolean wasClosed, FormResponseSimple response);
}