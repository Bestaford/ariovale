package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

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
        window.setTitle(translationService.getString("login.form.title", player));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {

    }
}