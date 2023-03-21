package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponse;
import ru.bestaford.ariovale.form.base.Form;

public interface FormService {

    <T extends Form> T createForm(Class<T> formClass);

    <T extends Form> void sendForm(T form, Player player);

    void handleResponse(int formID, Player player, boolean wasClosed, FormResponse response);

}