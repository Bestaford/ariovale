package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class RegistrationForm extends CustomForm {

    private final transient Player player;

    @Inject
    private TranslationService translationService;

    public RegistrationForm(Player player) {
        this.player = player;
    }

    @Override
    public void build() {
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