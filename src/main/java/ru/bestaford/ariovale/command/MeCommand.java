package ru.bestaford.ariovale.command;

import cn.nukkit.Player;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;

import javax.inject.Singleton;

@Singleton
public class MeCommand extends PlayerCommand {

    public MeCommand() {
        super("me");
        this.commandParameters.put("default", new CommandParameter[]{
                CommandParameter.newType("message", CommandParamType.RAWTEXT)
        });
    }

    @Override
    public boolean execute(Player player, String commandLabel, String[] args) {
        player.sendMessage("qwe");
        return true;
    }
}