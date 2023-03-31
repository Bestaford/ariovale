package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import ru.bestaford.ariovale.service.TranslationService;

public final class ExitForm extends ConfirmationForm {

    public ExitForm(TranslationService translationService) {
        super(translationService);
    }

    @Override
    public void build(Player player) {
        super.build(player);
        setTitle(translationService.getString("exit.form.title", player));
        setContent(translationService.getString("exit.form.content", player));
    }

    @Override
    public void handle(Player player, boolean confirmed) {
        player.sendMessage(String.valueOf(confirmed));
    }
}