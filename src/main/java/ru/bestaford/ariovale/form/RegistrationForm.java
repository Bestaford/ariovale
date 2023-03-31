package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class RegistrationForm extends CustomForm {

    private final TranslationService translationService;

    @Inject
    public RegistrationForm(TranslationService translationService) {
        this.translationService = translationService;
    }

    @Override
    public void build(Player targetPlayer) {
        setTitle(translationService.getString("authorization.registration.form.title", targetPlayer) + " [1/2]");
        addElement(new ElementLabel(translationService.getString("authorization.registration.form.label", targetPlayer)));
        addElement(new ElementInput(
                translationService.getString("authorization.password.text", targetPlayer),
                translationService.getString("authorization.password.placeholder", targetPlayer)
        ));
    }

    @Override
    public void handle(Player targetPlayer, boolean wasClosed, FormResponseCustom response) {
        targetPlayer.sendMessage("Registration");
    }
}