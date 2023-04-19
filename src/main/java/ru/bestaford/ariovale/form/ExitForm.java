package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import ru.bestaford.ariovale.form.base.ConfirmationForm;
import ru.bestaford.ariovale.form.base.IgnoreStack;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

@IgnoreStack
public final class ExitForm extends ConfirmationForm {

    private final Runnable runnable;

    @Inject private TranslationService translationService;

    public ExitForm(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    protected void build(Player player) {
        super.build(player);
        window.setTitle(translationService.getString(player, "exit.form.title"));
        window.setContent(translationService.getString(player, "exit.form.content"));
    }

    @Override
    public void handle(Player player, boolean confirmed) {
        if (confirmed) {
            String message = translationService.getString(player, "exit.text");
            player.close(message, message);
        } else {
            runnable.run();
        }
    }
}