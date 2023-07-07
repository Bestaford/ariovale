package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseModal;
import com.google.inject.Inject;
import ru.bestaford.ariovale.manager.TranslationManager;

public abstract class ConfirmationForm extends ModalForm {

    @Inject private TranslationManager translationManager;

    @Override
    protected void build(Player player) {
        window.setButton1(translationManager.getString(player, "confirmation.yes"));
        window.setButton2(translationManager.getString(player, "confirmation.no"));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseModal response) {
        handle(player, (!wasClosed) && (response != null) && (response.getClickedButtonId() == 0));
    }

    public abstract void handle(Player player, boolean confirmed);
}