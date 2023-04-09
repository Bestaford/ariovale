package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import ru.bestaford.ariovale.entity.Account;

public interface AuthenticationService {

    boolean isValidSession(Player player);

    void initialize(Player player);

    void process(Player player);

    void authenticate(Player player, String name);

    void register(Player player, Account account);

}