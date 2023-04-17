package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindowCustom;

public abstract class CustomForm implements Form {

    protected final FormWindowCustom window;

    public CustomForm() {
        window = new FormWindowCustom("");
    }

    public abstract void handle(Player player, boolean wasClosed, FormResponseCustom response);
}