package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;
import ru.bestaford.ariovale.service.TranslationService;

public abstract class SimpleForm extends FormWindowSimple implements Form {

    protected final transient TranslationService translationService;

    public SimpleForm(TranslationService translationService) {
        super("", "");
        this.translationService = translationService;
    }

    public abstract void build(Player player);

    public abstract void handle(Player player, boolean wasClosed, FormResponseSimple response);
}