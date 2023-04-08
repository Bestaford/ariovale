package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Form;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;
import java.util.Objects;

@Required
public final class RegistrationForm extends CustomForm {

    private final transient FormService formService;
    private transient Account account;
    private transient String error;

    @Inject
    public RegistrationForm(TranslationService translationService, FormService formService) {
        super(translationService);
        this.formService = formService;
    }

    public void setAccount(Account account) {
        this.account = account;
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
        if (other instanceof RegistrationForm form) {
            account = form.account;
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
        String password = response.getInputResponse(1);
        account.setPassword(password);
        error = null;
        if (password.isBlank()) {
            error = "registration.form.input.error.empty";
            formService.sendCopy(this, player);
            return;
        }
        if (!password.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!\\\"#$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~])\\S{8,}$")) {
            error = "registration.form.input.error.invalid";
            formService.sendCopy(this, player);
            return;
        }
        ProfileCreationForm profileCreationForm = formService.createForm(ProfileCreationForm.class);
        profileCreationForm.setAccount(account);
        formService.sendForm(profileCreationForm, player);
    }
}