package ru.bestaford.ariovale.listener;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.lang.CommandOutputContainer;
import cn.nukkit.utils.TextFormat;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.bestaford.ariovale.command.PlayerCommand;

import java.util.ArrayList;

@Singleton
public final class CommandListener implements Listener {

    @Inject private Server server;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String cmdLine = event.getMessage().stripLeading();
        cmdLine = cmdLine.charAt(0) == '/' ? cmdLine.substring(1) : cmdLine;
        ArrayList<String> parsed = SimpleCommandMap.parseArguments(cmdLine);
        if (parsed.size() == 0) {
            return;
        }
        String sentCommandLabel = parsed.remove(0).toLowerCase();
        Command target = server.getCommandMap().getCommand(sentCommandLabel);
        if ((target != null) && !(target instanceof PlayerCommand)) {
            player.sendCommandOutput(new CommandOutputContainer(TextFormat.RED + "%commands.generic.unknown", new String[]{sentCommandLabel}, 0));
            event.setCancelled();
        }
    }
}