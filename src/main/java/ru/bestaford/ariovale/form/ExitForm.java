package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import ru.bestaford.ariovale.form.base.ConfirmationForm;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class ExitForm extends ConfirmationForm {

    private final Runnable runnable;
    @Inject private TranslationService translationService;

    public ExitForm(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    protected void build(Player player) {
        super.build(player);
        window.setTitle(translationService.getString("exit.form.title", player));
        window.setContent(translationService.getString("exit.form.content", player));
    }

    @Override
    public void handle(Player player, boolean confirmed) {
        if (confirmed) {
            String message = translationService.getString("exit.text", player);
            player.close(message, message);
        } else {
            runnable.run();
        }
    }
}