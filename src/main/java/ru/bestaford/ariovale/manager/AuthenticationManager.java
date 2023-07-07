package ru.bestaford.ariovale.manager;

import cn.nukkit.Player;
import cn.nukkit.PlayerFood;
import cn.nukkit.Server;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.entity.PlayerState;
import ru.bestaford.ariovale.form.InformationForm;
import ru.bestaford.ariovale.form.LoginForm;
import ru.bestaford.ariovale.scoreboard.AccountScoreboard;
import ru.bestaford.ariovale.task.authentication.*;
import ru.bestaford.ariovale.util.OnlinePlayerData;
import ru.bestaford.ariovale.util.Strings;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public final class AuthenticationManager {

    public final Map<UUID, OnlinePlayerData> onlinePlayers = new ConcurrentHashMap<>();

    @Inject private FormManager formManager;
    @Inject private TaskManager taskManager;
    @Inject private TranslationManager translationManager;
    @Inject private CommandManager commandManager;

    public void update(Player player) {
        boolean loggedIn = isLoggedIn(player);
        player.setGamemode(loggedIn ? Player.SURVIVAL : Player.SPECTATOR);
        player.setAllowModifyWorld(false);
        player.setAllowInteract(false);
        player.setImmobile(!loggedIn);
        player.setCheckMovement(false);
        player.setOp(false);
        teleportToSafeSpawn(player);
        commandManager.updateAvailableCommands(player, loggedIn);
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
        Position safeSpawn = player.getLevel().getSafeSpawn(player.getPosition());
        player.teleport(new Location(safeSpawn.getX(), safeSpawn.getY(), safeSpawn.getZ(), 0, 0, 0));
    }

    public void process(Player player) {
        taskManager.scheduleAsyncTask(new IdentificationTask(player));
    }

    public void authenticate(Player player, String name) {
        taskManager.scheduleAsyncTask(new AuthenticationTask(player, name));
    }

    public void register(Player player, Account account) {
        taskManager.scheduleAsyncTask(new RegistrationTask(player, account));
    }

    public void completeRegistration(Player player, Account account) {
        formManager.sendForm(new InformationForm(translationManager.getString(player, "registration.complete", Strings.PORTAL_NAME_COLORIZED)), player);
        completeLogin(player, account, true);
        reset(player);
    }

    public void login(Player player, LoginForm loginForm) {
        taskManager.scheduleAsyncTask(new LoginTask(player, loginForm));
    }

    public void completeLogin(Player player, Account account, boolean silent) {
        Map<UUID, Player> serverPlayers = Server.getInstance().getOnlinePlayers();
        for (Map.Entry<UUID, OnlinePlayerData> onlinePlayer : onlinePlayers.entrySet()) {
            UUID uuid = onlinePlayer.getKey();
            String accountName = onlinePlayer.getValue().accountName();
            if (accountName.equals(account.getName())) {
                onlinePlayers.remove(uuid);
                if (serverPlayers.containsKey(uuid)) {
                    Player playerToKick = serverPlayers.get(uuid);
                    String message = Strings.THEME_ERROR + translationManager.getString(playerToKick, "login.kick");
                    playerToKick.close("", message);
                }
            }
        }
        onlinePlayers.put(account.getUniqueId(), new OnlinePlayerData(account.getName(), nextOnlinePlayerIndex()));
        formManager.clearStack(player);
        update(player);
        PlayerState playerState = account.getPlayerState();
        if (playerState != null) {
            playerState.restore(player);
        }
        if (!silent) {
            player.sendToast(Strings.FORMAT_BOLD + Strings.PORTAL_NAME_COLORIZED, translationManager.getString(player, "login.complete"));
        }
        new AccountScoreboard(account).display(player);
    }

    public void processQuit(Player player) {
        UUID uuid = player.getUniqueId();
        if (onlinePlayers.containsKey(uuid)) {
            taskManager.scheduleAsyncTask(new QuitTask((Player) player.clone(), onlinePlayers.get(uuid).accountName()));
            onlinePlayers.remove(uuid);
        }
    }

    public boolean isLoggedIn(Player player) {
        return player.isOnline() && !player.isOp() && onlinePlayers.containsKey(player.getUniqueId());
    }

    public int nextOnlinePlayerIndex() {
        int index;
        for (index = 1; index <= Server.getInstance().getMaxPlayers(); index++) {
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