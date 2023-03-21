package ru.bestaford.ariovale.form.base;

import cn.nukkit.form.window.FormWindowSimple;

public class SimpleForm implements Form {

    protected FormWindowSimple window;

    public void build(String title, String content) {
        window = new FormWindowSimple(title, content);
    }
}