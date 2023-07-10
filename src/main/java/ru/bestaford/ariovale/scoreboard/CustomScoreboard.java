package ru.bestaford.ariovale.scoreboard;

import cn.nukkit.Player;
import cn.nukkit.scoreboard.data.DisplaySlot;
import cn.nukkit.scoreboard.scoreboard.Scoreboard;
import ru.bestaford.ariovale.util.Strings;

public class CustomScoreboard extends Scoreboard implements Strings {

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
        player.display(this, DisplaySlot.SIDEBAR);
    }
}