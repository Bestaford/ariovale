package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;
import java.util.Objects;

@Required
public class LoginForm extends CustomForm {

    private final Account account;

    private String password;
    private String error;

    @Inject private TranslationService translationService;

    public LoginForm(Account account) {
        this.account = account;
    }

    @Override
    protected void build(Player player) {
        window.setTitle(FORMAT_BOLD + translationService.getString(player, "login.form.title"));
        window.addElement(new ElementLabel(Objects.requireNonNullElseGet(error, () -> translationService.getString(player, "login.form.label") + COLON + SPACE + THEME_PRIMARY + account.getName())));
        window.addElement(new ElementInput(
                translationService.getString(player, "registration.form.input.text"),
                translationService.getString(player, "registration.form.input.placeholder"),
                Objects.requireNonNullElse(password, EMPTY)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {

    }
}