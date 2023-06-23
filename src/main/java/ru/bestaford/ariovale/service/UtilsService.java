package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import ru.bestaford.ariovale.util.Strings;

@Singleton
public final class UtilsService {

    @Inject private TranslationService translationService;

    public void closeWithError(Player player) {
        String message = Strings.THEME_ERROR + translationService.getString(player, "error.text");
        player.close("", message);
    }
}