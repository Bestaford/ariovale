package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindow;
import ru.bestaford.ariovale.util.Strings;

public interface Form extends Strings {

    FormWindow getWindow(Player player);

}