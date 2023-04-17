package ru.bestaford.ariovale.service;

import cn.nukkit.Player;

import javax.inject.Singleton;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Singleton
public final class TranslationService {

    private static final String BUNDLE_BASE_NAME = "strings";
    private static final Pattern pattern = Pattern.compile("%\\{(.*?)}");

    public String getString(String key, Player player) {
        String[] languageCodeParts = player.getLoginChainData().getLanguageCode().split("_");
        Locale locale = new Locale(languageCodeParts[0], languageCodeParts[1]);
        String source = ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale).getString(key).trim();
        StringBuilder result = new StringBuilder();
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            matcher.appendReplacement(result, getString(matcher.group(1), player));
        }
        matcher.appendTail(result);
        return result.toString();
    }
}