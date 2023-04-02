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
    private transient String error;

    @Inject
    public AuthenticationForm(TranslationService translationService, FormService formService) {
        super(translationService);
        this.formService = formService;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public void build(Player player) {
        setTitle(translationService.getString("authentication.form.title", player));
        if (error == null) {
            addElement(new ElementLabel(translationService.getString("authentication.form.label", player)));
        } else {
            addElement(new ElementLabel(translationService.getString(error, player)));
        }
        addElement(new ElementInput(
                translationService.getString("authentication.form.input.text", player),
                translationService.getString("authentication.form.input.placeholder", player)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        if (wasClosed) {
            ExitForm exitForm = formService.createForm(ExitForm.class);
            exitForm.setCallback(() -> formService.sendForm(getClass(), player));
            formService.sendForm(exitForm, player);
            return;
        }
        String name = response.getInputResponse(1);
        if (name.isBlank()) {
            AuthenticationForm authenticationForm = formService.createForm(AuthenticationForm.class);
            authenticationForm.setError("authentication.form.input.error.empty");
            formService.sendForm(authenticationForm, player);
            return;
        }
    }
}