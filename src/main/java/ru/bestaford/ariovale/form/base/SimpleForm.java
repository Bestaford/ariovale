package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;

public abstract class SimpleForm extends FormWindowSimple implements Form {

    public SimpleForm() {
        super("", "");
    }

    public abstract FormWindow build();

    public abstract void handle(Player player, boolean wasClosed, FormResponseSimple response);
}