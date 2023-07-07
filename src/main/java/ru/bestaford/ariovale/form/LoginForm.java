package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.manager.AuthenticationManager;
import ru.bestaford.ariovale.manager.FormManager;
import ru.bestaford.ariovale.manager.TranslationManager;

import java.util.Objects;

@Required
public final class LoginForm extends CustomForm {

    public final Account account;

    public String password;
    public String error;

    @Inject private TranslationManager translationManager;
    @Inject private FormManager formManager;
    @Inject private AuthenticationManager authenticationManager;

    public LoginForm(Account account) {
        Preconditions.checkArgument(account != null);
        this.account = account;
    }

    @Override
    protected void build(Player player) {
        window.setTitle(FORMAT_BOLD + PORTAL_NAME + ": " + translationManager.getString(player, "login.form.title"));
        window.addElement(new ElementLabel(Objects.requireNonNullElseGet(error, () -> translationManager.getString(player, "login.form.label") + ": " + THEME_PRIMARY + account.getName())));
        window.addElement(new ElementInput(
                translationManager.getString(player, "registration.form.input.text"),
                translationManager.getString(player, "registration.form.input.placeholder"),
                Objects.requireNonNullElse(password, "")
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        Preconditions.checkArgument(player != null && !wasClosed && response != null);
        password = response.getInputResponse(1);
        error = null;
        if (password.isBlank()) {
            password = null;
            error = THEME_ERROR + translationManager.getString(player, "registration.form.input.error.empty");
            formManager.sendForm(this, player);
            return;
        }
        authenticationManager.login(player, this);
    }
}