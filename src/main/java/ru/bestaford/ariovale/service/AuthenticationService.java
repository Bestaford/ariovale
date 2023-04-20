package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.math.Vector3;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.AuthenticationForm;
import ru.bestaford.ariovale.task.AuthenticationTask;
import ru.bestaford.ariovale.task.RegistrationTask;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class AuthenticationService {

    @Inject private FormService formService;
    @Inject private TaskService taskService;
    @Inject private TranslationService translationService;
    @Inject private GameService gameService;

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
        formService.sendForm(new AuthenticationForm(), player);
    }

    public void authenticate(Player player, String name) {
        taskService.scheduleAsyncTask(new AuthenticationTask(player, name));
    }

    public void register(Player player, Account account) {
        taskService.scheduleAsyncTask(new RegistrationTask(player, account));
    }

    public void completeRegistration(Player player, Account account) {

    }
}