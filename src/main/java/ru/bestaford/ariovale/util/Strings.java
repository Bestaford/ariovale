package ru.bestaford.ariovale.util;

import cn.nukkit.utils.TextFormat;

public interface Strings {

    String ERROR_TRANSACTION = "An error occurred during transaction";
    String ERROR_STARTUP = "An error occurred during startup";
    String ERROR_FORM = "An error occurred during form handling";

    String FORMAT_BOLD = TextFormat.BOLD.toString();
    String FORMAT_RESET = TextFormat.RESET.toString();

    String THEME_PRIMARY = TextFormat.AQUA.toString();
    String THEME_SUCCESS = TextFormat.GREEN.toString();
    String THEME_ERROR = TextFormat.RED.toString();
    String THEME_OOC = TextFormat.GRAY.toString();

    String CITY_NAME = "Аriovale";
    String CITY_NAME_COLORIZED = THEME_PRIMARY + FORMAT_BOLD + CITY_NAME + FORMAT_RESET;

    String PORTAL_NAME = CITY_NAME + " ID";
    String PORTAL_NAME_COLORIZED = THEME_PRIMARY + FORMAT_BOLD + PORTAL_NAME + FORMAT_RESET;

    String EXAMPLE_NAMES = "\n\n" + THEME_SUCCESS + "Юрий Абрамов\nJudy Evans\nАделмар Гюнтер\nSanako Murakami";

    String REGISTRATION_STAGE_1 = "[1/2]";
    String REGISTRATION_STAGE_2 = "[2/2]";
}