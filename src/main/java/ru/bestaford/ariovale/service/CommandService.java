package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.command.data.CommandData;
import cn.nukkit.command.data.CommandDataVersions;
import cn.nukkit.network.protocol.AvailableCommandsPacket;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public final class CommandService {

    @Inject private Server server;
    @Inject private TranslationService translationService;

    public void updateAvailableCommands(Player player, boolean loggedIn) {
        SimpleCommandMap commandMap = server.getCommandMap();
        AvailableCommandsPacket packet = new AvailableCommandsPacket();
        Map<String, CommandDataVersions> commands = new HashMap<>();
        if (loggedIn) {
            for (Command command : commandMap.getCommands().values()) {
                if (!command.testPermissionSilent(player)) {
                    continue;
                }
                CommandDataVersions dataVersions = command.generateCustomCommandData(player);
                for (CommandData data : dataVersions.versions) {
                    data.description = translationService.getString(player, "command." + command.getName());
                }
                commands.put(command.getName(), dataVersions);
            }
        }
        packet.commands = commands;
        player.dataPacket(packet);
    }
}