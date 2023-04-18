package ru.bestaford.ariovale.service;

import cn.nukkit.Player;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class UtilsService {

    @Inject TranslationService translationService;

    public void closeWithError(Player player) {
        String message = translationService.getString("error.text", player);
        player.close(message, message);
    }
}