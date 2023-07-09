package ru.bestaford.ariovale.manager;

import cn.nukkit.Player;
import ru.bestaford.ariovale.util.Strings;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class UtilsManager {

    @Inject private TranslationManager translationManager;

    public void closeWithError(Player player) {
        String message = Strings.THEME_ERROR + translationManager.getString(player, "error.text");
        player.close("", message);
    }
}