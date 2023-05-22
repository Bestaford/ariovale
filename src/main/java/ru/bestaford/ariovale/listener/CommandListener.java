package ru.bestaford.ariovale.listener;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import ru.bestaford.ariovale.command.CustomCommand;
import ru.bestaford.ariovale.service.TranslationService;
import ru.bestaford.ariovale.util.Strings;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class CommandListener implements Listener {

    @Inject private TranslationService translationService;
    @Inject private Server server;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage().trim().substring(1);
        if (message.isBlank()) {
            event.setCancelled();
            return;
        }
        Command foundCommand = null;
        SimpleCommandMap commandMap = server.getCommandMap();
        for (Command command : commandMap.getCommands().values()) {
            if (!(command instanceof CustomCommand)) {
                continue;
            }
            if (command.getName().equalsIgnoreCase(message)) {
                foundCommand = command;
                break;
            } else {
                for (String alias : command.getAliases()) {
                    if (alias.equalsIgnoreCase(message)) {
                        foundCommand = command;
                        break;
                    }
                }
            }
        }
        if (foundCommand == null) {
            event.setCancelled();
            player.sendMessage(Strings.THEME_ERROR + translationService.getString(player, "command.unknown"));
        }
    }
}