package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import com.google.inject.Inject;
import org.apache.commons.text.WordUtils;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.manager.AuthenticationManager;
import ru.bestaford.ariovale.manager.FormManager;
import ru.bestaford.ariovale.manager.TranslationManager;

import java.util.Objects;

@Required
public final class AuthenticationForm extends CustomForm {

    public String name;
    public String error;

    @Inject private FormManager formManager;
    @Inject private TranslationManager translationManager;
    @Inject private AuthenticationManager authenticationManager;

    @Override
    protected void build(Player player) {
        window.setTitle(FORMAT_BOLD + PORTAL_NAME);
        window.addElement(new ElementLabel(Objects.requireNonNullElseGet(error, () -> translationManager.getString(player, "authentication.form.label", PORTAL_NAME_COLORIZED, CITY_NAME_COLORIZED, THEME_OOC))));
        window.addElement(new ElementInput(
                translationManager.getString(player, "authentication.form.input.text"),
                translationManager.getString(player, "authentication.form.input.placeholder"),
                Objects.requireNonNullElse(name, "")
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        name = response.getInputResponse(1).trim();
        error = null;
        if (name.isBlank()) {
            name = null;
            error = THEME_ERROR + translationManager.getString(player, "authentication.form.input.error.empty");
            formManager.sendForm(this, player);
            return;
        }
        if (!Account.NAME_PATTERN.matcher(name).matches()) {
            error = THEME_ERROR + translationManager.getString(player, "authentication.form.input.error.invalid", EXAMPLE_NAMES);
            formManager.sendForm(this, player);
            return;
        }
        name = WordUtils.capitalizeFully(name);
        authenticationManager.authenticate(player, name);
    }
}