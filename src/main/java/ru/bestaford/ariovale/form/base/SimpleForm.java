package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;

public abstract class SimpleForm extends FormWindowSimple implements Form {

    protected final transient Player player;

    public SimpleForm(Player player) {
        super("", "");
        this.player = player;
    }

    public abstract void build();

    public abstract void handle(Player player, boolean wasClosed, FormResponseSimple response);
}