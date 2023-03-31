package ru.bestaford.ariovale.service.impl;

import cn.nukkit.Player;
import cn.nukkit.math.Vector3;
import ru.bestaford.ariovale.service.AuthorizationService;
import ru.bestaford.ariovale.service.TaskService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class AuthorizationServiceImpl implements AuthorizationService {

    private final TaskService taskService;

    @Inject
    public AuthorizationServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public boolean isValidSession(Player player) {
        return true; //TODO: implement
    }

    @Override
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
    }

    @Override
    public void process(Player player) {

    }
}