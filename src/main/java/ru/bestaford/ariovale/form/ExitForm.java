package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import ru.bestaford.ariovale.form.base.ConfirmationForm;
import ru.bestaford.ariovale.form.base.IgnoreStack;
import ru.bestaford.ariovale.manager.TranslationManager;

import javax.inject.Inject;

@IgnoreStack
public final class ExitForm extends ConfirmationForm {

    public final Runnable runnable;

    @Inject private TranslationManager translationManager;

    public ExitForm(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    protected void build(Player player) {
        super.build(player);
        window.setTitle(FORMAT_BOLD + translationManager.getString(player, "exit.form.title"));
        window.setContent(translationManager.getString(player, "exit.form.content"));
    }

    @Override
    public void handle(Player player, boolean confirmed) {
        if (confirmed) {
            String message = translationManager.getString(player, "exit.text");
            player.close("", message);
        } else {
            runnable.run();
        }
    }
}