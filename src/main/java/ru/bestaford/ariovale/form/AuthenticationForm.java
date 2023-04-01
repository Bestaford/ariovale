package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class AuthenticationForm extends CustomForm {

    private final transient FormService formService;

    @Inject
    public AuthenticationForm(TranslationService translationService, FormService formService) {
        super(translationService);
        this.formService = formService;
    }

    @Override
    public void build(Player player) {
        setTitle(translationService.getString("authentication.form.title", player));
        addElement(new ElementLabel(translationService.getString("authentication.form.label", player)));
        addElement(new ElementInput(translationService.getString("authentication.form.input.text", player), translationService.getString("authentication.form.input.placeholder", player)));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        if (wasClosed) {
            ExitForm exitForm = formService.createForm(ExitForm.class);
            exitForm.setCallback(() -> formService.sendForm(getClass(), player));
            formService.sendForm(exitForm, player);
        } else {
            formService.sendForm(getClass(), player);
        }
    }
}