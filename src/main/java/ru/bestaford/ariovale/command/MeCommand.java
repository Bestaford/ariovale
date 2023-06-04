package ru.bestaford.ariovale.command;

import cn.nukkit.Player;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;

public class MeCommand extends PlayerCommand {

    public MeCommand() {
        super("me");
        this.commandParameters.clear();
        this.commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newType("message", CommandParamType.MESSAGE)
        });
        this.enableParamTree();
    }

    @Override
    public boolean execute(Player player, String commandLabel, String[] args) {
        player.sendMessage("qwe");
        return true;
    }
}