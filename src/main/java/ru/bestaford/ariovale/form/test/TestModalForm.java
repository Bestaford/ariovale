package ru.bestaford.ariovale.form.test;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindow;
import ru.bestaford.ariovale.form.base.ModalForm;

public class TestModalForm extends ModalForm {

    @Override
    public FormWindow build() {
        setTitle("TestModalForm title");
        setContent("TestModalForm content");
        setButton1("TestModalForm button1");
        setButton2("TestModalForm button2");
        return this;
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseModal response) {
        player.sendMessage("TestModalForm response handler");
    }
}