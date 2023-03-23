package ru.bestaford.ariovale.form.test;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import ru.bestaford.ariovale.form.base.SimpleForm;

public class TestSimpleForm extends SimpleForm {

    @Override
    public FormWindow build() {
        setTitle("TestSimpleForm title");
        setContent("TestSimpleForm content");
        return this;
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseSimple response) {
        player.sendMessage("TestSimpleForm response handler");
    }
}