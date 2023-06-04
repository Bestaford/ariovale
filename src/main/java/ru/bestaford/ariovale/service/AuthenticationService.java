package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.PlayerFood;
import cn.nukkit.Server;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.entity.PlayerState;
import ru.bestaford.ariovale.form.InformationForm;
import ru.bestaford.ariovale.form.LoginForm;
import ru.bestaford.ariovale.task.authentication.*;
import ru.bestaford.ariovale.util.OnlinePlayerData;
import ru.bestaford.ariovale.util.Strings;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public final class AuthenticationService {

    public final Map<UUID, OnlinePlayerData> onlinePlayers = new ConcurrentHashMap<>();

    @Inject private FormService formService;
    @Inject private TaskService taskService;
    @Inject private TranslationService translationService;
    @Inject private CommandService commandService;
    @Inject private Server server;

    public void update(Player player) {
        boolean loggedIn = isLoggedIn(player);
        player.setGamemode(loggedIn ? Player.SURVIVAL : Player.SPECTATOR);
        player.setAllowModifyWorld(false);
        player.setAllowInteract(false);
        player.setImmobile(!loggedIn);
        player.setCheckMovement(false);
        player.setOp(false);
        teleportToSafeSpawn(player);
        commandService.updateAvailableCommands(player, loggedIn);
    }

    public void reset(Player player) {
        player.setHealth(player.getMaxHealth());
        PlayerFood foodData = player.getFoodData();
        foodData.setLevel(foodData.getMaxLevel());
        player.getInventory().clearAll();
        player.getOffhandInventory().clearAll();
        player.setExperience(0, 0);
        player.extinguish();
        player.resetFallDistance();
        player.removeAllEffects();
        teleportToSafeSpawn(player);
    }

    public void teleportToSafeSpawn(Player player) {
        player.teleport(player.getLevel().getSafeSpawn(player.getPosition()));
    }

    public void process(Player player) {
        taskService.scheduleAsyncTask(new IdentificationTask(player));
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
        Map<UUID, Player> serverPlayers = server.getOnlinePlayers();
        for (Map.Entry<UUID, OnlinePlayerData> onlinePlayer : onlinePlayers.entrySet()) {
            UUID uuid = onlinePlayer.getKey();
            String accountName = onlinePlayer.getValue().accountName();
            if (accountName.equals(account.getName())) {
                onlinePlayers.remove(uuid);
                if (serverPlayers.containsKey(uuid)) {
                    Player playerToKick = serverPlayers.get(uuid);
                    String message = Strings.THEME_ERROR + translationService.getString(playerToKick, "login.kick");
                    playerToKick.close("", message);
                }
            }
        }
        onlinePlayers.put(account.getUniqueId(), new OnlinePlayerData(account.getName(), nextOnlinePlayerIndex()));
        formService.clearStack(player);
        update(player);
        PlayerState playerState = account.getPlayerState();
        if (playerState != null) {
            playerState.restore(player);
        }
        if (!silent) {
            player.sendToast(Strings.FORMAT_BOLD + Strings.PORTAL_NAME_COLORIZED, translationService.getString(player, "login.complete"));
        }
    }

    public void processQuit(Player player) {
        UUID uuid = player.getUniqueId();
        if (onlinePlayers.containsKey(uuid)) {
            taskService.scheduleAsyncTask(new QuitTask((Player) player.clone(), onlinePlayers.get(uuid).accountName()));
            onlinePlayers.remove(uuid);
        }
    }

    public boolean isLoggedIn(Player player) {
        return player.isOnline() && onlinePlayers.containsKey(player.getUniqueId());
    }

    public int nextOnlinePlayerIndex() {
        int index;
        for (index = 1; index <= server.getMaxPlayers(); index++) {
            boolean used = false;
            for (OnlinePlayerData data : onlinePlayers.values()) {
                if (data.index() == index) {
                    used = true;
                    break;
                }
            }
            if (!used) {
                break;
            }
        }
        return index;
    }
}