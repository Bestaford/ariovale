package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.block.BlockAir;
import cn.nukkit.level.Level;
import cn.nukkit.math.Vector3;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.AuthenticationForm;
import ru.bestaford.ariovale.form.LoginForm;
import ru.bestaford.ariovale.task.AuthenticationTask;
import ru.bestaford.ariovale.task.LoginTask;
import ru.bestaford.ariovale.task.RegistrationTask;
import ru.bestaford.ariovale.util.Strings;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class AuthenticationService {

    @Inject private FormService formService;
    @Inject private TaskService taskService;
    @Inject private GameService gameService;

    public void initialize(Player player) {
        player.setGamemode(Player.SURVIVAL);
        player.setAllowModifyWorld(false);
        player.setAllowInteract(false);
        player.setCheckMovement(false);
        player.setOp(false);
        int floorX = player.getFloorX();
        int floorZ = player.getFloorZ();
        Level level = player.getLevel();
        for (int floorY = player.getFloorY(); floorY <= level.getMaxHeight(); floorY++) {
            Block block1 = level.getBlock(floorX, floorY, floorZ);
            Block block2 = level.getBlock(floorX, floorY + 1, floorZ);
            if (block1 instanceof BlockAir && block2 instanceof BlockAir) {
                player.teleport(new Vector3(player.getX(), floorY, player.getZ()));
                break;
            }
        }
    }

    public void process(Player player) {
        formService.sendForm(new AuthenticationForm(), player);
    }

    public void authenticate(Player player, String name) {
        taskService.scheduleAsyncTask(new AuthenticationTask(player, name));
    }

    public void register(Player player, Account account) {
        taskService.scheduleAsyncTask(new RegistrationTask(player, account));
    }

    public void completeRegistration(Player player, Account account) {
        gameService.information(player, "registration.complete", Strings.PORTAL_NAME_COLORIZED);
    }

    public void login(Player player, LoginForm loginForm) {
        taskService.scheduleAsyncTask(new LoginTask(player, loginForm));
    }
}