package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import org.apache.commons.lang3.StringUtils;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;
import java.util.Objects;

@Required
public final class RegistrationForm extends CustomForm {

    private final String name;

    private String password;
    private String error;

    @Inject private FormService formService;
    @Inject private TranslationService translationService;
    @Inject private AuthenticationService authenticationService;

    public RegistrationForm(String name) {
        this.name = name;
    }

    @Override
    protected void build(Player player) {
        window.setTitle(translationService.getString("registration.form.title", player));
        window.addElement(new ElementLabel(translationService.getString(Objects.requireNonNullElse(error, "registration.form.label"), player)));
        window.addElement(new ElementInput(
                translationService.getString("registration.form.input.text", player),
                translationService.getString("registration.form.input.placeholder", player),
                Objects.requireNonNullElse(password, StringUtils.EMPTY)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {

    }
}