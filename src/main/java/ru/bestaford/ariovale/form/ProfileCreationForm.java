package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.service.TranslationService;

public final class ProfileCreationForm extends CustomForm implements Required {

    public ProfileCreationForm(TranslationService translationService) {
        super(translationService);
    }

    @Override
    public void build(Player player) {

    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {

    }
}