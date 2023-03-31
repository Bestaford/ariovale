package ru.bestaford.ariovale.service.impl;

import cn.nukkit.Player;
import cn.nukkit.form.window.FormWindow;
import com.google.inject.Injector;
import ru.bestaford.ariovale.form.Form;
import ru.bestaford.ariovale.service.FormService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class FormServiceImpl implements FormService {

    private final Injector injector;

    @Inject
    public FormServiceImpl(Injector injector) {
        this.injector = injector;
    }

    @Override
    public <T extends Form> T createForm(Class<T> formClass) {
        return injector.getInstance(formClass);
    }

    @Override
    public <T extends Form> void sendForm(Class<T> formClass, Player player) {
        sendForm(createForm(formClass), player);
    }

    @Override
    public <T extends Form> void sendForm(T form, Player player) {
        form.build(player);
        player.showFormWindow((FormWindow) form);
    }
}