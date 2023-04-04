package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import ru.bestaford.ariovale.form.base.ConfirmationForm;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class ExitForm extends ConfirmationForm {

    private transient Runnable callback;

    @Inject
    public ExitForm(TranslationService translationService) {
        super(translationService);
    }

    public Runnable getCallback() {
        return callback;
    }

    public void setCallback(Runnable callback) {
        this.callback = callback;
    }

    @Override
    public void build(Player player) {
        super.build(player);
        setTitle(translationService.getString("exit.form.title", player));
        setContent(translationService.getString("exit.form.content", player));
    }

    @Override
    public void handle(Player player, boolean confirmed) {
        if (confirmed) {
            String message = translationService.getString("exit.text", player);
            player.close(message, message);
        } else {
            if (callback != null) {
                callback.run();
            }
        }
    }
}