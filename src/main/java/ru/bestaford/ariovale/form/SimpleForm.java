package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;

public abstract class SimpleForm extends FormWindowSimple implements Form {

    public SimpleForm() {
        super("", "");
    }

    public abstract void build(Player targetPlayer);

    public abstract void handle(Player targetPlayer, boolean wasClosed, FormResponseSimple response);
}