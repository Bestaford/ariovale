package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;
import java.util.Objects;

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
        addElement(new ElementLabel(translationService.getString(Objects.requireNonNullElse(error, "authentication.form.label"), player)));
        addElement(new ElementInput(
                translationService.getString("authentication.form.input.text", player),
                translationService.getString("authentication.form.input.placeholder", player)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        if (wasClosed) {
            ExitForm exitForm = formService.createForm(ExitForm.class);
            exitForm.setCallback(() -> formService.sendForm(AuthenticationForm.class, player));
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
        if (!name.matches("^[a-zA-Z]{1,20} [a-zA-Z]{1,20}$")) {
            AuthenticationForm authenticationForm = formService.createForm(AuthenticationForm.class);
            authenticationForm.setError("authentication.form.input.error.invalid");
            formService.sendForm(authenticationForm, player);
            return;
        }
    }
}