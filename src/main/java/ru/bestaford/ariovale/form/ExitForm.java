package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import ru.bestaford.ariovale.form.base.ConfirmationForm;
import ru.bestaford.ariovale.form.base.IgnoreStack;
import ru.bestaford.ariovale.manager.TranslationManager;

@IgnoreStack
public final class ExitForm extends ConfirmationForm {

    public final Runnable runnable;

    @Inject private TranslationManager translationManager;

    public ExitForm(Runnable runnable) {
        Preconditions.checkArgument(runnable != null);
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
        Preconditions.checkArgument(player != null);
        if (confirmed) {
            String message = translationManager.getString(player, "exit.text");
            player.close("", message);
        } else {
            runnable.run();
        }
    }
}