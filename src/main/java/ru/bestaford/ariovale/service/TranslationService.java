package ru.bestaford.ariovale.service;

import cn.nukkit.Player;

import javax.inject.Singleton;
import java.util.Locale;
import java.util.ResourceBundle;

@Singleton
public final class TranslationService {

    private static final String BUNDLE_BASE_NAME = "strings";

    public String getString(String key, Player player, Object... args) {
        return getString(key, player).formatted(args);
    }

    public String getString(String key, Player player) {
        String[] languageCodeParts = player.getLoginChainData().getLanguageCode().split("_");
        Locale locale = new Locale(languageCodeParts[0], languageCodeParts[1]);
        return ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale).getString(key).trim();
    }
}