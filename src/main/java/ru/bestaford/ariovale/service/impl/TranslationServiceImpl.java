package ru.bestaford.ariovale.service.impl;

import cn.nukkit.Player;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Singleton;
import java.util.Locale;
import java.util.ResourceBundle;

@Singleton
public final class TranslationServiceImpl implements TranslationService {

    private static final String BUNDLE_BASE_NAME = "strings";

    @Override
    public String getString(String key, Player player) {
        String[] languageCodeParts = player.getLoginChainData().getLanguageCode().split("_");
        Locale locale = new Locale(languageCodeParts[0], languageCodeParts[1]);
        return ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale).getString(key).trim();
    }
}