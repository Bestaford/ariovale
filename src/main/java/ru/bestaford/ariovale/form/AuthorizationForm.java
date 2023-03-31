package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class AuthorizationForm extends CustomForm {

    private final transient FormService formService;

    @Inject
    public AuthorizationForm(TranslationService translationService, FormService formService) {
        super(translationService);
        this.formService = formService;
    }

    @Override
    public void build(Player player) {
        setTitle(translationService.getString("authorization.form.title", player));
        addElement(new ElementLabel(translationService.getString("authorization.form.label", player)));
        addElement(new ElementInput(
                translationService.getString("authorization.form.input.text", player),
                translationService.getString("authorization.form.input.placeholder", player)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        formService.sendForm(this, player);
    }
}