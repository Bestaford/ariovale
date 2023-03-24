package ru.bestaford.ariovale.impl;

import cn.nukkit.Player;
import com.google.inject.Singleton;
import ru.bestaford.ariovale.service.TranslationService;

import java.util.Locale;
import java.util.ResourceBundle;

@Singleton
public final class TranslationServiceImpl implements TranslationService {

    private static final String BUNDLE_BASE_NAME = "strings";

    @Override
    public String getString(String key) {
        return ResourceBundle.getBundle(BUNDLE_BASE_NAME).getString(key);
    }

    @Override
    public String getString(String key, Player player) {
        String[] languageCodeParts = player.getLoginChainData().getLanguageCode().split("_");
        return ResourceBundle.getBundle(BUNDLE_BASE_NAME, new Locale(languageCodeParts[0], languageCodeParts[1])).getString(key);
    }
}