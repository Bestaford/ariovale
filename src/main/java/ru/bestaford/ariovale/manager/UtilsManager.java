package ru.bestaford.ariovale.manager;

import cn.nukkit.Player;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.bestaford.ariovale.util.Strings;

@Singleton
public final class UtilsManager {

    @Inject private TranslationManager translationManager;

    public void closeWithError(Player player) {
        Preconditions.checkArgument(player != null);
        String message = Strings.THEME_ERROR + translationManager.getString(player, "error.text");
        player.close("", message);
    }
}