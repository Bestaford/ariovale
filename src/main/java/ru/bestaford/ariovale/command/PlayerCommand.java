package ru.bestaford.ariovale.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;


public abstract class PlayerCommand extends Command {

    public PlayerCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return sender.isPlayer() && execute(sender.asPlayer(), commandLabel, args);
    }

    public abstract boolean execute(Player player, String commandLabel, String[] args);
}