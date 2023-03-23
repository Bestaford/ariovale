package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowModal;

public class ModalForm implements Form {

    protected FormWindowModal window;

    protected void build(String title, String content, String trueButtonText, String falseButtonText) {
        window = new FormWindowModal(title, content, trueButtonText, falseButtonText);
    }

    public void handle(Player player, boolean wasClosed, FormResponseModal response) {

    }

    @Override
    public FormWindow getWindow() {
        return window;
    }
}