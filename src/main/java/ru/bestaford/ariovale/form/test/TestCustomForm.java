package ru.bestaford.ariovale.form.test;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindow;
import ru.bestaford.ariovale.form.base.CustomForm;

public class TestCustomForm extends CustomForm {

    @Override
    public FormWindow build() {
        setTitle("TestCustomForm title");
        return this;
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        player.sendMessage("TestCustomForm response handler");
    }
}