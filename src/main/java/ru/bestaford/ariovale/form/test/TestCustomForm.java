package ru.bestaford.ariovale.form.test;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.form.base.CustomForm;

public class TestCustomForm extends CustomForm {

    public TestCustomForm build() {
        build("TestCustomForm title");
        window.addElement(new ElementInput("TestCustomForm input"));
        return this;
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        player.sendMessage("TestCustomForm response handler: " + response.toString());
    }
}