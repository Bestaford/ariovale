package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindowCustom;

public abstract class CustomForm extends FormWindowCustom implements Form {

    public CustomForm() {
        super("");
    }

    public abstract void build(Player targetPlayer);

    public abstract void handle(Player targetPlayer, boolean wasClosed, FormResponseCustom response);
}