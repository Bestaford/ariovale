package ru.bestaford.ariovale.form.test;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseModal;
import ru.bestaford.ariovale.form.base.ModalForm;

public class TestModalForm extends ModalForm {

    public TestModalForm build() {
        build(
                "TestModalForm title",
                "TestModalForm content",
                "TestModalForm trueButtonText",
                "TestModalForm falseButtonText"
        );
        return this;
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseModal response) {
        player.sendMessage("TestModalForm response handler: " + response.toString());
    }
}