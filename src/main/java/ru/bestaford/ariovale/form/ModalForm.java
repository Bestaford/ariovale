package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindowModal;

public abstract class ModalForm extends FormWindowModal implements Form {

    public ModalForm() {
        super("", "", "", "");
    }

    public abstract void build(Player player);

    public abstract void handle(Player player, boolean wasClosed, FormResponseModal response);
}