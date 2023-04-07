package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Form;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;
import java.util.Objects;

public final class RegistrationForm extends CustomForm {

    private final transient FormService formService;
    private transient Account account;
    private transient String error;

    @Inject
    public RegistrationForm(TranslationService translationService, FormService formService) {
        super(translationService);
        this.formService = formService;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public void build(Player player) {
        setTitle(translationService.getString("registration.form.title", player) + " [1/2]");
        addElement(new ElementLabel(translationService.getString(Objects.requireNonNullElse(error, "registration.form.label"), player)));
        addElement(new ElementInput(
                translationService.getString("registration.form.input.text", player),
                translationService.getString("registration.form.input.placeholder", player),
                Objects.requireNonNullElse(account.getPassword(), "")
        ));
    }

    @Override
    public Form copy(Form other) {
        return this;
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        if (wasClosed) {
            ExitForm exitForm = formService.createForm(ExitForm.class);
            exitForm.setCallback(() -> {
                RegistrationForm registrationForm = formService.createForm(RegistrationForm.class);
                registrationForm.setAccount(account);
                registrationForm.setError(error);
                formService.sendForm(registrationForm, player);
            });
            formService.sendForm(exitForm, player);
            return;
        }
        String password = response.getInputResponse(1);
        account.setPassword(password);
        if (password.isBlank()) {
            RegistrationForm registrationForm = formService.createForm(RegistrationForm.class);
            registrationForm.setAccount(account);
            registrationForm.setError("registration.form.input.error.empty");
            formService.sendForm(registrationForm, player);
            return;
        }
        if (!password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!\\\"#$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~])\\S{8,}$")) {
            RegistrationForm registrationForm = formService.createForm(RegistrationForm.class);
            registrationForm.setAccount(account);
            registrationForm.setError("registration.form.input.error.invalid");
            formService.sendForm(registrationForm, player);
            return;
        }
        ProfileCreationForm profileCreationForm = formService.createForm(ProfileCreationForm.class);
        profileCreationForm.setAccount(account);
        formService.sendForm(profileCreationForm, player);
    }
}