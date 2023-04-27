package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.PlayerFood;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.AuthenticationForm;
import ru.bestaford.ariovale.form.InformationForm;
import ru.bestaford.ariovale.form.LoginForm;
import ru.bestaford.ariovale.task.AuthenticationTask;
import ru.bestaford.ariovale.task.LoginTask;
import ru.bestaford.ariovale.task.RegistrationTask;
import ru.bestaford.ariovale.util.Strings;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public final class AuthenticationService {

    public final Map<UUID, String> onlinePlayers = new ConcurrentHashMap<>();

    @Inject private FormService formService;
    @Inject private TaskService taskService;
    @Inject private TranslationService translationService;

    public void update(Player player) {
        boolean loggedIn = isLoggedIn(player);
        player.setGamemode(loggedIn ? Player.SURVIVAL : Player.SPECTATOR);
        player.setAllowModifyWorld(loggedIn);
        player.setAllowInteract(loggedIn);
        player.setImmobile(!loggedIn);
        player.setCheckMovement(false);
        player.setOp(false);
        teleportToSafeSpawn(player);
    }

    public void reset(Player player) {
        player.setHealth(player.getMaxHealth());
        PlayerFood foodData = player.getFoodData();
        foodData.setLevel(foodData.getMaxLevel());
        player.getInventory().clearAll();
        teleportToSafeSpawn(player);
    }

    public void teleportToSafeSpawn(Player player) {
        player.teleport(player.getLevel().getSafeSpawn(player.getPosition()));
    }

    public void process(Player player) {
        UUID uuid = player.getUniqueId();
        AuthenticationForm authenticationForm = new AuthenticationForm();
        if (onlinePlayers.containsKey(uuid)) {
            String name = onlinePlayers.get(uuid);
            authenticationForm.name = name;
            formService.sendForm(authenticationForm, player, true);
            authenticate(player, name);
        } else {
            formService.sendForm(authenticationForm, player);
        }
    }

    public void authenticate(Player player, String name) {
        taskService.scheduleAsyncTask(new AuthenticationTask(player, name));
    }

    public void register(Player player, Account account) {
        taskService.scheduleAsyncTask(new RegistrationTask(player, account));
    }

    public void completeRegistration(Player player, Account account) {
        formService.sendForm(new InformationForm(translationService.getString(player, "registration.complete", Strings.PORTAL_NAME_COLORIZED)), player);
        completeLogin(player, account, true);
        reset(player);
    }

    public void login(Player player, LoginForm loginForm) {
        taskService.scheduleAsyncTask(new LoginTask(player, loginForm));
    }

    public void completeLogin(Player player, Account account, boolean silent) {
        if (!silent) {
            player.sendToast(Strings.FORMAT_BOLD + Strings.PORTAL_NAME_COLORIZED, translationService.getString(player, "login.complete"));
        }
        formService.clearStack(player);
        onlinePlayers.put(account.getUUID(), account.getName());
        update(player);
    }

    public boolean isLoggedIn(Player player) {
        return onlinePlayers.containsKey(player.getUniqueId());
    }
}
