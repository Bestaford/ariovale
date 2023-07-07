package ru.bestaford.ariovale.manager;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.command.data.CommandData;
import cn.nukkit.command.data.CommandDataVersions;
import cn.nukkit.network.protocol.AvailableCommandsPacket;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.bestaford.ariovale.command.PlayerCommand;

import java.util.HashMap;
import java.util.Map;

@Singleton
public final class CommandManager {

    @Inject private TranslationManager translationManager;

    public void updateAvailableCommands(Player player, boolean loggedIn) {
        SimpleCommandMap commandMap = Server.getInstance().getCommandMap();
        AvailableCommandsPacket packet = new AvailableCommandsPacket();
        Map<String, CommandDataVersions> commands = new HashMap<>();
        if (loggedIn) {
            for (Command command : commandMap.getCommands().values()) {
                if (!(command.testPermissionSilent(player)) || !(command instanceof PlayerCommand)) {
                    continue;
                }
                CommandDataVersions dataVersions = command.generateCustomCommandData(player);
                for (CommandData data : dataVersions.versions) {
                    data.description = translationManager.getString(player, "command." + command.getName());
                }
                commands.put(command.getName(), dataVersions);
            }
        }
        packet.commands = commands;
        player.dataPacket(packet);
    }
}