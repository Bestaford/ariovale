package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindowModal;
import ru.bestaford.ariovale.service.TranslationService;

public abstract class ModalForm extends FormWindowModal implements Form {

    protected final transient TranslationService translationService;

    public ModalForm(TranslationService translationService) {
        super("", "", "", "");
        this.translationService = translationService;
    }

    public abstract void build(Player player);

    public abstract void handle(Player player, boolean wasClosed, FormResponseModal response);
}