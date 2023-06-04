package ru.bestaford.ariovale.command;

import cn.nukkit.Player;

public class TestCommand extends PlayerCommand {

    public TestCommand() {
        super("qwe");
    }

    @Override
    public boolean execute(Player player, String commandLabel, String[] args) {
        player.sendMessage("test command output");
        return false;
    }
}