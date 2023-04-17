package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.math.Vector3;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.AuthenticationForm;
import ru.bestaford.ariovale.task.AuthenticationTask;
import ru.bestaford.ariovale.task.LoginTask;
import ru.bestaford.ariovale.task.RegistrationTask;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public final class AuthenticationService {

    private final FormService formService;
    private final TaskService taskService;
    private final Map<UUID, Account> loggedPlayers;

    @Inject
    public AuthenticationService(FormService formService, TaskService taskService) {
        this.formService = formService;
        this.taskService = taskService;
        loggedPlayers = new ConcurrentHashMap<>();
    }

    public boolean isValidSession(Player player) {
        return true; //TODO: implement
    }

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
        formService.sendForm(AuthenticationForm.class, player);
    }

    public void authenticate(Player player, String name) {
        AuthenticationTask authenticationTask = taskService.createTask(AuthenticationTask.class);
        authenticationTask.setPlayer(player);
        authenticationTask.setName(name);
        taskService.scheduleAsyncTask(authenticationTask);
    }

    public void register(Player player, Account account) {
        RegistrationTask registrationTask = taskService.createTask(RegistrationTask.class);
        registrationTask.setPlayer(player);
        registrationTask.setAccount(account);
        taskService.scheduleAsyncTask(registrationTask);
    }

    public void completeRegistration(Player player, Account account) {
        player.sendMessage("completeRegistration");
    }

    public void login(Player player, Account account) {
        LoginTask loginTask = taskService.createTask(LoginTask.class);
        loginTask.setPlayer(player);
        loginTask.setAccount(account);
        taskService.scheduleAsyncTask(loginTask);
    }

    public void completeLogin(Player player, Account account) {
        player.sendMessage("completeLogin");
    }
}