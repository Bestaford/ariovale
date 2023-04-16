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

@Required
public final class LoginForm extends CustomForm {

    private final transient FormService formService;
    private transient Account account;
    private transient String error;

    @Inject
    public LoginForm(TranslationService translationService, FormService formService) {
        super(translationService);
        this.formService = formService;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public void build(Player player) {
        setTitle(translationService.getString("login.form.title", player));
        if (error == null) {
            addElement(new ElementLabel(translationService.getString("login.form.label", player) + ": " + account.getName()));
        } else {
            addElement(new ElementLabel(translationService.getString(error, player)));
        }
        addElement(new ElementInput(
                translationService.getString("login.form.input.text", player),
                translationService.getString("login.form.input.placeholder", player)
        ));
    }

    @Override
    public Form copy(Form other) {
        if (other instanceof LoginForm) {
            account = ((LoginForm) other).account;
            error = ((LoginForm) other).error;
        }
        return this;
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        String password = response.getInputResponse(1);
        account.setPassword(password);
        error = null;
        if (password.isBlank()) {
            account.setPassword(null);
            error = "login.form.input.error.empty";
            formService.sendCopy(this, player);
            return;
        }
    }
}