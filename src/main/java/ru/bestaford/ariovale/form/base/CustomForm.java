package ru.bestaford.ariovale.form.base;

import cn.nukkit.form.window.FormWindowCustom;

public class CustomForm implements Form {

    protected FormWindowCustom window;

    public CustomForm build(String title) {
        window = new FormWindowCustom(title);
        return this;
    }
}