package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;
import java.util.Objects;

public final class AuthenticationForm extends CustomForm {

    private final transient FormService formService;
    private final transient AuthenticationService authenticationService;
    private transient String name;
    private transient String error;

    @Inject
    public AuthenticationForm(TranslationService translationService, FormService formService, AuthenticationService authenticationService) {
        super(translationService);
        this.formService = formService;
        this.authenticationService = authenticationService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                translationService.getString("authentication.form.input.placeholder", player),
                Objects.requireNonNullElse(name, "")
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        if (wasClosed) {
            ExitForm exitForm = formService.createForm(ExitForm.class);
            exitForm.setCallback(() -> {
                AuthenticationForm authenticationForm = formService.createForm(AuthenticationForm.class);
                authenticationForm.setName(name);
                authenticationForm.setError(error);
                formService.sendForm(authenticationForm, player);
            });
            formService.sendForm(exitForm, player);
            return;
        }
        String name = response.getInputResponse(1);
        if (name.isBlank()) {
            AuthenticationForm authenticationForm = formService.createForm(AuthenticationForm.class);
            authenticationForm.setName(name);
            authenticationForm.setError("authentication.form.input.error.empty");
            formService.sendForm(authenticationForm, player);
            return;
        }
        if (!name.matches("^\\p{L}{1,30} \\p{L}{1,30}$")) {
            AuthenticationForm authenticationForm = formService.createForm(AuthenticationForm.class);
            authenticationForm.setName(name);
            authenticationForm.setError("authentication.form.input.error.invalid");
            formService.sendForm(authenticationForm, player);
            return;
        }
        StringBuilder finalName = new StringBuilder();
        String[] nameParts = name.split(" ");
        finalName
                .append(nameParts[0].substring(0, 1).toUpperCase())
                .append(nameParts[0].substring(1).toLowerCase())
                .append(" ")
                .append(nameParts[1].substring(0, 1).toUpperCase())
                .append(nameParts[1].substring(1).toLowerCase());
        authenticationService.authenticate(player, finalName.toString());
    }
}