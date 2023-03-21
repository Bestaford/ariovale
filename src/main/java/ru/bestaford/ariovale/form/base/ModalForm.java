package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindowModal;

public class ModalForm implements Form {

    protected FormWindowModal window;

    public ModalForm build(String title, String content, String trueButtonText, String falseButtonText) {
        window = new FormWindowModal(title, content, trueButtonText, falseButtonText);
        return this;
    }

    public void handle(Player player, boolean wasClosed, FormResponseModal response) {

    }
}