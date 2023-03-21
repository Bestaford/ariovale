package ru.bestaford.ariovale.form.base;

import cn.nukkit.form.window.FormWindowSimple;

public class SimpleForm implements Form {

    protected FormWindowSimple window;

    public SimpleForm build(String title, String content) {
        window = new FormWindowSimple(title, content);
        return this;
    }
}