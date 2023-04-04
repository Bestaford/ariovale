package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;
import java.util.Objects;

public final class RegistrationForm extends CustomForm implements Required {

    private final transient FormService formService;
    private transient String name;
    private transient String error;

    @Inject
    public RegistrationForm(TranslationService translationService, FormService formService) {
        super(translationService);
        this.formService = formService;
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
        setTitle(translationService.getString("registration.form.title", player));
        addElement(new ElementLabel(translationService.getString(Objects.requireNonNullElse(error, "registration.form.label"), player)));
        addElement(new ElementInput(
                translationService.getString("registration.form.input.text", player),
                translationService.getString("registration.form.input.placeholder", player)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        String password = response.getInputResponse(1);
        if (password.isBlank()) {
            RegistrationForm registrationForm = formService.createForm(RegistrationForm.class);
            registrationForm.setName(name);
            registrationForm.setError("registration.form.input.error.empty");
            formService.sendForm(registrationForm, player);
            return;
        }
    }
}