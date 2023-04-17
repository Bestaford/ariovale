package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import org.apache.commons.lang3.StringUtils;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
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

    @Override
    protected void build(Player player) {
        window.setTitle(translationService.getString("authentication.form.title", player));
        window.addElement(new ElementLabel(translationService.getString(Objects.requireNonNullElse(error, "authentication.form.label"), player)));
        window.addElement(new ElementInput(
                translationService.getString("authentication.form.input.text", player),
                translationService.getString("authentication.form.input.placeholder", player),
                Objects.requireNonNullElse(name, StringUtils.EMPTY)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        player.sendMessage(response.getInputResponse(1));
    }
}