package ru.bestaford.ariovale.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import com.google.common.base.Preconditions;

public abstract class PlayerCommand extends Command {

    public PlayerCommand(String name) {
        super(name);
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Preconditions.checkArgument(sender != null);
        return sender.isPlayer() && execute(sender.asPlayer(), commandLabel, args);
    }

    public abstract boolean execute(Player player, String commandLabel, String[] args);
}