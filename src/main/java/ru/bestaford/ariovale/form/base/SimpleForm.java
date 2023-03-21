package ru.bestaford.ariovale.form.base;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindowSimple;

public class SimpleForm implements Form {

    protected FormWindowSimple window;

    public SimpleForm build(String title, String content) {
        window = new FormWindowSimple(title, content);
        return this;
    }

    public void handle(Player player, boolean wasClosed, FormResponseSimple response) {

    }
}