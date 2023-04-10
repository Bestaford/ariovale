package ru.bestaford.ariovale.util;

import cn.nukkit.Player;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class Utils {

    private final TranslationService translationService;

    @Inject
    public Utils(TranslationService translationService) {
        this.translationService = translationService;
    }

    public void softError(Player player) {
        player.sendMessage(translationService.getString("error.text", player));
    }

    public void hardError(Player player) {
        String message = translationService.getString("error.text", player);
        player.close(message, message);
    }
}