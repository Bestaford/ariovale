package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import ru.bestaford.ariovale.form.base.SimpleForm;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class InformationForm extends SimpleForm {

    private final String content;

    @Inject private TranslationService translationService;

    public InformationForm(String content) {
        this.content = content;
    }

    @Override
    protected void build(Player player) {
        window.setTitle(translationService.getString("information.form.title", player));
        window.setContent(translationService.getString(content, player));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseSimple response) {
        //nothing to do
    }
}