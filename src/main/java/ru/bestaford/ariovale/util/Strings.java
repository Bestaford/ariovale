package ru.bestaford.ariovale.util;

public interface Strings {

    String EMPTY = "";
    String SPACE = " ";
    String LF = "\n";
    String CR = "\r";

    String ESCAPE = "§";

    String COLOR_BLACK = ESCAPE + "0";
    String COLOR_DARK_BLUE = ESCAPE + "1";
    String COLOR_DARK_GREEN = ESCAPE + "2";
    String COLOR_DARK_AQUA = ESCAPE + "3";
    String COLOR_DARK_RED = ESCAPE + "4";
    String COLOR_DARK_PURPLE = ESCAPE + "5";
    String COLOR_GOLD = ESCAPE + "6";
    String COLOR_GRAY = ESCAPE + "7";
    String COLOR_DARK_GRAY = ESCAPE + "8";
    String COLOR_BLUE = ESCAPE + "9";
    String COLOR_GREEN = ESCAPE + "a";
    String COLOR_AQUA = ESCAPE + "b";
    String COLOR_RED = ESCAPE + "c";
    String COLOR_LIGHT_PURPLE = ESCAPE + "d";
    String COLOR_YELLOW = ESCAPE + "e";
    String COLOR_WHITE = ESCAPE + "f";
    String COLOR_MINECOIN_GOLD = ESCAPE + "g";

    String FORMAT_OBFUSCATED = ESCAPE + "k";
    String FORMAT_BOLD = ESCAPE + "l";
    String FORMAT_STRIKETHROUGH = ESCAPE + "m";
    String FORMAT_UNDERLINE = ESCAPE + "n";
    String FORMAT_ITALIC = ESCAPE + "o";
    String FORMAT_RESET = ESCAPE + "r";

    String THEME_PRIMARY = COLOR_AQUA;
    String THEME_SUCCESS = COLOR_GREEN;
    String THEME_ERROR = COLOR_RED;
    String THEME_OOC = COLOR_GRAY;

    String CITY_NAME = "Ariovale";
    String CITY_NAME_COLORIZED = THEME_PRIMARY + FORMAT_BOLD + CITY_NAME + FORMAT_RESET;

    String PORTAL_NAME = CITY_NAME + SPACE + "ID";
    String PORTAL_NAME_COLORIZED = THEME_PRIMARY + FORMAT_BOLD + PORTAL_NAME + FORMAT_RESET;

    String EXAMPLE_NAMES = "Юрий Абрамов" + LF + "Judy Evans" + LF + "Аделмар Гюнтер" + LF + "Sanako Murakami";
}