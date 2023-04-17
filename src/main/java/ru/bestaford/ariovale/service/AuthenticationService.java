package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.math.Vector3;

import javax.inject.Singleton;

@Singleton
public final class AuthenticationService {

    public void initialize(Player player) {
        player.setGamemode(Player.SURVIVAL);
        player.setAllowModifyWorld(false);
        player.setAllowInteract(false);
        player.setCheckMovement(false);
        player.setOp(false);
        int x = player.getFloorX();
        int z = player.getFloorZ();
        int y = player.getLevel().getHighestBlockAt(x, z);
        player.teleport(new Vector3(x, y, z));
        player.pitch = 0;
    }

    public void process(Player player) {
        player.sendMessage("process");
    }
}