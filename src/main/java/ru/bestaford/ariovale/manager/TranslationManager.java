package ru.bestaford.ariovale.manager;

import cn.nukkit.Player;
import com.google.inject.Singleton;

import java.util.Locale;
import java.util.ResourceBundle;

@Singleton
public final class TranslationManager {

    private static final String BUNDLE_BASE_NAME = "strings";

    public String getString(Player player, String key, Object... args) {
        String[] languageCodeParts = player.getLoginChainData().getLanguageCode().split("_");
        Locale locale = new Locale(languageCodeParts[0], languageCodeParts[1]);
        return ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale).getString(key).trim().formatted(args);
    }
}