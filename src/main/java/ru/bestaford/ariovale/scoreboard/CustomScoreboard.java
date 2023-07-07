package ru.bestaford.ariovale.scoreboard;

import cn.nukkit.Player;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.scoreboard.scoreboard.Scoreboard;
import com.google.common.base.Preconditions;

public class CustomScoreboard extends Scoreboard {

    private int line;

    public CustomScoreboard(String name) {
        super(name, name);
    }

    public void addLine() {
        addLine("");
    }

    public void addLine(String text) {
        addLine(text, ++line);
    }

    public void display(Player player) {
        Preconditions.checkArgument(player != null);
        player.display(this, DisplaySlot.SIDEBAR);
    }
}