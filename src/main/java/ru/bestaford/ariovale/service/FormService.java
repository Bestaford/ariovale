package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import com.google.inject.Injector;
import ru.bestaford.ariovale.form.base.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public final class FormService {

    private final Injector injector;
    private final Map<FormWindow, Form> windowMap;
    private final Map<Player, Stack<Form>> formStackMap;

    @Inject
    public FormService(Injector injector) {
        this.injector = injector;
        windowMap = new ConcurrentHashMap<>();
        formStackMap = new ConcurrentHashMap<>();
    }

    public void sendForm(Form form, Player player) {
        injector.injectMembers(form);
        FormWindow window = form.getWindow(player);
        windowMap.put(window, form);
        player.showFormWindow(window);
        if (!form.getClass().isAnnotationPresent(IgnoreStack.class)) {
            if (!formStackMap.containsKey(player)) {
                formStackMap.put(player, new Stack<>());
            }
            Stack<Form> formStack = formStackMap.get(player);
            formStack.push(form);
        }
    }

    public void handleResponse(FormWindow window, Player player, boolean wasClosed, FormResponse response) {
        if (windowMap.containsKey(window)) {
            Form form = windowMap.get(window);
            if (!form.getClass().isAnnotationPresent(IgnoreStack.class)) {
                Stack<Form> formStack = formStackMap.get(player);
                formStack.remove(form);
            }
            if (form instanceof SimpleForm) {
                ((SimpleForm) form).handle(player, wasClosed, (FormResponseSimple) response);
            } else if (form instanceof ModalForm) {
                ((ModalForm) form).handle(player, wasClosed, (FormResponseModal) response);
            } else if (form instanceof CustomForm) {
                ((CustomForm) form).handle(player, wasClosed, (FormResponseCustom) response);
            }
            windowMap.remove(window);
        }
    }
}