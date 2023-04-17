package ru.bestaford.ariovale.service;

import cn.nukkit.Player;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class UtilsService {

    private final TranslationService translationService;

    @Inject
    public UtilsService(TranslationService translationService) {
        this.translationService = translationService;
    }

    public void hardError(Player player) {
        String message = translationService.getString("error.text", player);
        player.close(message, message);
    }
}