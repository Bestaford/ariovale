package ru.bestaford.ariovale.util;

import cn.nukkit.utils.TextFormat;
import org.apache.commons.lang3.StringUtils;

public enum Strings {

    THEME_PRIMARY(TextFormat.AQUA),
    THEME_SUCCESS(TextFormat.GREEN),
    THEME_ERROR(TextFormat.RED),
    THEME_TITLE(TextFormat.BOLD),
    THEME_OOC(TextFormat.GRAY),

    CITY_NAME_RAW("Ariоvale"),
    CITY_NAME_TITLE(THEME_TITLE, CITY_NAME_RAW),
    CITY_NAME_COLORIZED(THEME_PRIMARY, TextFormat.BOLD, CITY_NAME_RAW, TextFormat.RESET),

    PORTAL_NAME_RAW(CITY_NAME_RAW, StringUtils.SPACE, "ID"),
    PORTAL_NAME_TITLE(THEME_TITLE, PORTAL_NAME_RAW),
    PORTAL_NAME_COLORIZED(THEME_PRIMARY, TextFormat.BOLD, PORTAL_NAME_RAW, TextFormat.RESET);

    private final String string;

    Strings(String string) {
        this.string = string;
    }

    Strings(Object... objects) {
        StringBuilder result = new StringBuilder();
        for (Object object : objects) {
            result.append(object.toString());
        }
        string = result.toString();
    }

    @Override
    public String toString() {
        return string;
    }
}