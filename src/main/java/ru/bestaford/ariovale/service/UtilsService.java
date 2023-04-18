package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.utils.MainLogger;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class UtilsService {

    @Inject TranslationService translationService;
    @Inject MainLogger logger;

    public void throwError(Player player, Throwable throwable) {
        logger.logException(throwable);
        throwError(player);
    }

    public void throwError(Player player) {
        String message = translationService.getString("error.text", player);
        player.close(message, message);
    }
}