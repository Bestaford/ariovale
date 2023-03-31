package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class AuthorizationForm extends CustomForm {

    private final TranslationService translationService;

    @Inject
    public AuthorizationForm(TranslationService translationService) {
        this.translationService = translationService;
    }

    @Override
    public void build(Player player) {
        setTitle(translationService.getString("authorization.form.title", player));
        addElement(new ElementLabel(translationService.getString("authorization.form.label", player)));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        player.sendMessage("AuthorizationForm response handler");
    }
}