package ru.bestaford.ariovale.service;

import cn.nukkit.Player;

public interface AuthorizationService {

    boolean isValidSession(Player player);

    void initialize(Player player);

    void process(Player player);

}