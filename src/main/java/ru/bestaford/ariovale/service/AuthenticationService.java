package ru.bestaford.ariovale.service;

import cn.nukkit.Player;

public interface AuthenticationService {

    boolean isValidSession(Player player);

    void initialize(Player player);

    void process(Player player);

    void authenticate(Player player, String name);

}