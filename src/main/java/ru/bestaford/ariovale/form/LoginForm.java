package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import jakarta.inject.Inject;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import java.util.Objects;

@Required
public final class LoginForm extends CustomForm {

    public final Account account;

    public String password;
    public String error;

    @Inject private TranslationService translationService;
    @Inject private FormService formService;
    @Inject private AuthenticationService authenticationService;

    public LoginForm(Account account) {
        this.account = Objects.requireNonNull(account);
    }

    @Override
    protected void build(Player player) {
        window.setTitle(FORMAT_BOLD + PORTAL_NAME + COLON + SPACE + translationService.getString(player, "login.form.title"));
        window.addElement(new ElementLabel(Objects.requireNonNullElseGet(error, () -> translationService.getString(player, "login.form.label") + COLON + SPACE + THEME_PRIMARY + account.getName())));
        window.addElement(new ElementInput(
                translationService.getString(player, "registration.form.input.text"),
                translationService.getString(player, "registration.form.input.placeholder"),
                Objects.requireNonNullElse(password, EMPTY)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        password = response.getInputResponse(1);
        error = null;
        if (password.isBlank()) {
            password = null;
            error = THEME_ERROR + translationService.getString(player, "registration.form.input.error.empty");
            formService.sendForm(this, player);
            return;
        }
        authenticationService.login(player, this);
    }
}