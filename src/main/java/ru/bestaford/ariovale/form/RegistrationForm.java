package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.manager.FormManager;
import ru.bestaford.ariovale.manager.TranslationManager;

import javax.inject.Inject;
import java.util.Objects;

@Required
public final class RegistrationForm extends CustomForm {

    public final Account account;

    public String password;
    public String error;

    @Inject private FormManager formManager;
    @Inject private TranslationManager translationManager;

    public RegistrationForm(Account account) {
        this.account = account;
    }

    @Override
    protected void build(Player player) {
        window.setTitle(FORMAT_BOLD + PORTAL_NAME + ": " + translationManager.getString(player, "registration.form.title") + " " + REGISTRATION_STAGE_1);
        window.addElement(new ElementLabel(Objects.requireNonNullElseGet(error, () ->
                translationManager.getString(player, "registration.form.label", PORTAL_NAME_COLORIZED) + "\n\n" + translationManager.getString(player, "registration.form.label.part2"))));
        window.addElement(new ElementInput(
                translationManager.getString(player, "registration.form.input.text"),
                translationManager.getString(player, "registration.form.input.placeholder"),
                Objects.requireNonNullElse(password, "")
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        password = response.getInputResponse(1);
        error = null;
        if (password.isBlank()) {
            password = null;
            error = THEME_ERROR + translationManager.getString(player, "registration.form.input.error.empty");
            formManager.sendForm(this, player);
            return;
        }
        if (!Account.PASSWORD_PATTERN.matcher(password).matches()) {
            error = THEME_ERROR + translationManager.getString(player, "registration.form.input.error.invalid");
            formManager.sendForm(this, player);
            return;
        }
        account.setPassword(password);
        formManager.sendForm(new ProfileCreationForm(account), player);
    }
}