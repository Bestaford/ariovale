package ru.bestaford.ariovale.service;

import cn.nukkit.Player;

public interface TranslationService {

    String getString(String key);

    String getString(String key, Player player);

}