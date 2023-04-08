package ru.bestaford.ariovale.service.impl;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import com.google.inject.Injector;
import ru.bestaford.ariovale.form.base.*;
import ru.bestaford.ariovale.service.FormService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Singleton
public final class FormServiceImpl implements FormService {

    private final Injector injector;
    private final Map<Player, Stack<Form>> formStackMap;

    @Inject
    public FormServiceImpl(Injector injector) {
        this.injector = injector;
        formStackMap = new HashMap<>();
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
    public <T extends Form> void sendCopy(T form, Player player) {
        sendForm(createForm(form.getClass()).copy(form), player);
    }

    @Override
    public <T extends Form> void sendForm(T form, Player player) {
        form.build(player);
        player.showFormWindow((FormWindow) form);
        if (!form.getClass().isAnnotationPresent(IgnoreStack.class)) {
            if (!formStackMap.containsKey(player)) {
                formStackMap.put(player, new Stack<>());
            }
            Stack<Form> formStack = formStackMap.get(player);
            if(formStack.empty()) {
                formStack.push(form);
            } else {
                if(formStack.peek().getClass().equals(form.getClass())) {
                    formStack.pop();
                }
                formStack.push(form);
            }
            player.sendMessage(formStack.toString());
        }
    }

    @Override
    public void handleResponse(FormWindow window, Player player, boolean wasClosed, FormResponse response) {
        if (window instanceof Form) {
            if (window instanceof SimpleForm) {
                ((SimpleForm) window).handle(player, wasClosed, (FormResponseSimple) response);
            } else if (window instanceof ModalForm) {
                ((ModalForm) window).handle(player, wasClosed, (FormResponseModal) response);
            } else if (window instanceof CustomForm) {
                ((CustomForm) window).handle(player, wasClosed, (FormResponseCustom) response);
            }
        }
    }
}