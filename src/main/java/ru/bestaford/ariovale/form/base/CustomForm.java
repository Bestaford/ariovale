package ru.bestaford.ariovale.form.base;

import cn.nukkit.form.window.FormWindowCustom;

public class CustomForm implements Form {

    protected FormWindowCustom window;

    public void build(String title) {
        window = new FormWindowCustom(title);
    }
}