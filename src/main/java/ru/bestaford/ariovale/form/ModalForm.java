package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindowModal;

public abstract class ModalForm extends FormWindowModal implements Form {

    protected final transient Player player;

    public ModalForm(Player player) {
        super("", "", "", "");
        this.player = player;
    }

    public abstract void build();

    public abstract void handle(Player player, boolean wasClosed, FormResponseModal response);
}