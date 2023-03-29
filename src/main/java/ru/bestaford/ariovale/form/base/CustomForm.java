package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindowCustom;

public abstract class CustomForm extends FormWindowCustom implements Form {

    public CustomForm() {
        super("");
    }

    public abstract void build();

    public abstract void handle(Player player, boolean wasClosed, FormResponseCustom response);
}