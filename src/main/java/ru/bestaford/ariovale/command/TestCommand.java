package ru.bestaford.ariovale.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class TestCommand extends Command {

    public TestCommand() {
        super("qwe", "desc");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("test");
        }
        return true;
    }
}