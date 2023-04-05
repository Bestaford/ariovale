package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class ProfileCreationForm extends CustomForm {

    @Inject
    public ProfileCreationForm(TranslationService translationService) {
        super(translationService);
    }

    @Override
    public void build(Player player) {
        setTitle(translationService.getString("profile.creation.form.title", player) + " [2/2]");
        addElement(new ElementLabel(translationService.getString("profile.creation.form.label", player)));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {

    }
}