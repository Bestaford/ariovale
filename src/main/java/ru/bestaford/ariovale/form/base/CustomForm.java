package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;

public class CustomForm implements Form {

    protected FormWindowCustom window;

    public CustomForm build(String title) {
        window = new FormWindowCustom(title);
        return this;
    }

    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {

    }

    @Override
    public FormWindow getWindow() {
        return window;
    }
}