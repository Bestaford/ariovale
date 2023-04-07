package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Form;
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
    public Form copy(Form other) {
        if (other instanceof AuthenticationForm form) {
            name = form.name;
            error = form.error;
        }
        return this;
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        if (wasClosed) {
            ExitForm exitForm = formService.createForm(ExitForm.class);
            exitForm.setCallback(() -> formService.sendCopy(this, player));
            formService.sendForm(exitForm, player);
            return;
        }
        name = response.getInputResponse(1);
        error = null;
        if (name.isBlank()) {
            error = "authentication.form.input.error.empty";
            formService.sendCopy(this, player);
            return;
        }
        if (!name.matches("^\\p{L}{1,30} \\p{L}{1,30}$")) {
            error = "authentication.form.input.error.invalid";
            formService.sendCopy(this, player);
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
        name = finalName.toString();
        authenticationService.authenticate(player, name);
    }
}