package ru.bestaford.ariovale.service;

import cn.nukkit.Server;
import cn.nukkit.command.Command;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public final class CommandService {

    public final Map<String, Command> knownCommands = new HashMap<>();

    @Inject private Server server;

    public void register(Command command) {
        knownCommands.put(command.getName(), command);
        server.getCommandMap().register("ariovale", command);
    }
}