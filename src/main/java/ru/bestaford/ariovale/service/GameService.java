package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import ru.bestaford.ariovale.util.Strings;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class GameService {

    @Inject private TranslationService translationService;

    public void closeWithError(Player player) {
        String message = Strings.THEME_ERROR + translationService.getString(player, "error.text");
        player.close(message, message);
    }
}