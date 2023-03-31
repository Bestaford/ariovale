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
    public void build(Player player) {
        setTitle(translationService.getString("authorization.registration.form.title", player) + " [1/2]");
        addElement(new ElementLabel(translationService.getString("authorization.registration.form.label", player)));
        addElement(new ElementInput(
                translationService.getString("authorization.password.text", player),
                translationService.getString("authorization.password.placeholder", player)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        player.sendMessage("Registration");
    }
}