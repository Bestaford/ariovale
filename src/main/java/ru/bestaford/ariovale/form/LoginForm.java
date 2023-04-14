package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Form;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class LoginForm extends CustomForm {

    @Inject
    public LoginForm(TranslationService translationService) {
        super(translationService);
    }

    @Override
    public void build(Player player) {
        setTitle(translationService.getString("login.form.title", player));
        addElement(new ElementLabel(translationService.getString("login.form.label", player)));
        addElement(new ElementInput(
                translationService.getString("login.form.input.text", player),
                translationService.getString("login.form.input.placeholder", player)
        ));
    }

    @Override
    public Form copy(Form other) {
        return this;
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        player.sendMessage("handle");
    }
}