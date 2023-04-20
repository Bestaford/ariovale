package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;
import java.util.Objects;

@Required
public final class RegistrationForm extends CustomForm {

    private final Account account;

    private String error;

    @Inject private FormService formService;
    @Inject private TranslationService translationService;

    public RegistrationForm(Account account) {
        this.account = account;
    }

    @Override
    protected void build(Player player) {
        window.setTitle(translationService.getString(player, "registration.form.title") + " [1/2]");
        window.addElement(new ElementLabel(translationService.getString(player, Objects.requireNonNullElse(error, "registration.form.label"))));
        window.addElement(new ElementInput(
                translationService.getString(player, "registration.form.input.text"),
                translationService.getString(player, "registration.form.input.placeholder"),
                Objects.requireNonNullElse(account.getPassword(), EMPTY)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        account.setPassword(response.getInputResponse(1));
        error = null;
        if (account.getPassword().isBlank()) {
            account.setPassword(null);
            error = "registration.form.input.error.empty";
            formService.sendForm(this, player);
            return;
        }
        if (!Account.PASSWORD_PATTERN.matcher(account.getPassword()).matches()) {
            error = "registration.form.input.error.invalid";
            formService.sendForm(this, player);
            return;
        }
        formService.sendForm(new ProfileCreationForm(account), player);
    }
}