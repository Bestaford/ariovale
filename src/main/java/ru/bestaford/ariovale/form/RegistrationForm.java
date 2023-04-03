package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class RegistrationForm extends CustomForm {

    private transient String name;

    @Inject
    public RegistrationForm(TranslationService translationService) {
        super(translationService);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void build(Player player) {
        setTitle(translationService.getString("registration.form.title", player));
        addElement(new ElementLabel(translationService.getString("registration.form.label", player)));
        addElement(new ElementInput(
                translationService.getString("registration.form.input.text", player),
                translationService.getString("registration.form.input.placeholder", player)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {

    }
}