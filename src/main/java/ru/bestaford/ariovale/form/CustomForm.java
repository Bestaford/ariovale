package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindowCustom;

public abstract class CustomForm extends FormWindowCustom implements Form {

    protected final transient Player player;

    public CustomForm(Player player) {
        super("");
        this.player = player;
    }

    public abstract void build();

    public abstract void handle(Player player, boolean wasClosed, FormResponseCustom response);
}