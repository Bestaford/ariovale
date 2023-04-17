package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindowModal;

public abstract class ModalForm implements Form {

    protected final FormWindowModal window;

    public ModalForm() {
        window = new FormWindowModal("", "", "", "");
    }

    public abstract void handle(Player player, boolean wasClosed, FormResponseModal response);
}