package ru.bestaford.ariovale.form.test;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.response.FormResponseSimple;
import ru.bestaford.ariovale.form.base.SimpleForm;

public class TestSimpleForm extends SimpleForm {

    public TestSimpleForm build() {
        build("TestSimpleForm title", "TestSimpleForm content");
        window.addButton(new ElementButton("Button 1"));
        window.addButton(new ElementButton("Button 2"));
        return this;
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseSimple response) {
        player.sendMessage("TestSimpleForm response handler: " + response.toString());
    }
}