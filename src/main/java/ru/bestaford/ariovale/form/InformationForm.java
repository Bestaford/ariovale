package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import ru.bestaford.ariovale.form.base.SimpleForm;

import java.util.Objects;

public final class InformationForm extends SimpleForm {

    public final String content;

    public InformationForm(String content) {
        this.content = Objects.requireNonNull(content);
    }

    @Override
    protected void build(Player player) {
        window.setTitle(FORMAT_BOLD + CITY_NAME);
        window.setContent(content);
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseSimple response) {
        //nothing to do
    }
}