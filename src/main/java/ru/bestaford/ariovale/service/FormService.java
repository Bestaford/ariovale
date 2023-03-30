package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.window.FormWindow;
import ru.bestaford.ariovale.form.base.Form;

public interface FormService {

    <T extends Form> void sendForm(T form, Player player);

    void handleResponse(FormWindow window, Player player, boolean wasClosed, FormResponse response);

}