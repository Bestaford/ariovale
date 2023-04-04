package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindowCustom;
import ru.bestaford.ariovale.service.TranslationService;

public abstract class CustomForm extends FormWindowCustom implements Form {

    protected final transient TranslationService translationService;

    public CustomForm(TranslationService translationService) {
        super("");
        this.translationService = translationService;
    }

    public abstract void build(Player player);

    public abstract void handle(Player player, boolean wasClosed, FormResponseCustom response);
}