package ru.bestaford.ariovale.service.impl;

import cn.nukkit.Player;
import ru.bestaford.ariovale.service.TranslationService;
import ru.bestaford.ariovale.service.UtilsService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class UtilsServiceImpl implements UtilsService {

    private final TranslationService translationService;

    @Inject
    public UtilsServiceImpl(TranslationService translationService) {
        this.translationService = translationService;
    }

    @Override
    public void hardError(Player player) {
        String message = translationService.getString("error.text", player);
        player.close(message, message);
    }
}