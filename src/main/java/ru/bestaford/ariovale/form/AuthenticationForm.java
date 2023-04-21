package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import org.apache.commons.lang3.StringUtils;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;
import java.util.Objects;

@Required
public final class AuthenticationForm extends CustomForm {

    private String name;
    private String error;

    @Inject private FormService formService;
    @Inject private TranslationService translationService;
    @Inject private AuthenticationService authenticationService;

    @Override
    protected void build(Player player) {
        window.setTitle(FORMAT_BOLD + PORTAL_NAME);
        window.addElement(new ElementLabel(Objects.requireNonNullElseGet(error, () -> translationService.getString(player, "authentication.form.label", PORTAL_NAME_COLORIZED, CITY_NAME_COLORIZED, THEME_OOC))));
        window.addElement(new ElementInput(
                translationService.getString(player, "authentication.form.input.text"),
                translationService.getString(player, "authentication.form.input.placeholder"),
                Objects.requireNonNullElse(name, EMPTY)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        name = response.getInputResponse(1).trim();
        error = null;
        if (name.isBlank()) {
            name = null;
            error = THEME_ERROR + translationService.getString(player, "authentication.form.input.error.empty");
            formService.sendForm(this, player);
            return;
        }
        if (!Account.NAME_PATTERN.matcher(name).matches()) {
            error = THEME_ERROR + translationService.getString(player, "authentication.form.input.error.invalid", EXAMPLE_NAMES);
            formService.sendForm(this, player);
            return;
        }
        StringBuilder finalName = new StringBuilder();
        String[] nameParts = StringUtils.split(name);
        finalName
                .append(nameParts[0].substring(0, 1).toUpperCase())
                .append(nameParts[0].substring(1).toLowerCase())
                .append(SPACE)
                .append(nameParts[1].substring(0, 1).toUpperCase())
                .append(nameParts[1].substring(1).toLowerCase());
        name = finalName.toString().trim();
        authenticationService.authenticate(player, name);
    }
}