package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseModal;
import ru.bestaford.ariovale.service.TranslationService;

public abstract class ConfirmationForm extends ModalForm {

    public ConfirmationForm(TranslationService translationService) {
        super(translationService);
    }

    @Override
    public void build(Player player) {
        setButton1(translationService.getString("confirmation.form.yes", player));
        setButton2(translationService.getString("confirmation.form.no", player));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseModal response) {
        handle(player, (!wasClosed) && (response != null) && (response.getClickedButtonId() == 0));
    }

    public abstract void handle(Player player, boolean confirmed);
}