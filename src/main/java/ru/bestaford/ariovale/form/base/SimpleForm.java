package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;

public abstract class SimpleForm implements Form {

    protected final FormWindowSimple window;

    public SimpleForm() {
        window = new FormWindowSimple("", "");
    }

    public abstract void handle(Player player, boolean wasClosed, FormResponseSimple response);
}