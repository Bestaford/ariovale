package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import ru.bestaford.ariovale.form.Form;

public interface FormService {

    <T extends Form> T createForm(Class<T> formClass);

    <T extends Form> void sendForm(Class<T> formClass, Player player);

    <T extends Form> void sendForm(T form, Player player);

}