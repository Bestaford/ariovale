package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;

public interface Form {

    void build(Player player);

    Form copy(Form other);

}