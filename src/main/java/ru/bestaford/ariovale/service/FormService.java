package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.window.FormWindow;
import ru.bestaford.ariovale.form.base.Form;

public interface FormService {

    <T extends Form> T createForm(Class<T> formClass);

    <T extends Form> void sendForm(Class<T> formClass, Player player);

    <T extends Form> void sendCopy(T form, Player player);

    <T extends Form> void sendForm(T form, Player player);

    void handleResponse(FormWindow window, Player player, boolean wasClosed, FormResponse response);

}