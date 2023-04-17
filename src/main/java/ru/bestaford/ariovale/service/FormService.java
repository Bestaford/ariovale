package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import com.google.inject.Injector;
import ru.bestaford.ariovale.form.ExitForm;
import ru.bestaford.ariovale.form.base.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

@Singleton
public final class FormService {

    private final Injector injector;
    private final Map<Player, Stack<Form>> formStackMap;

    @Inject
    public FormService(Injector injector) {
        this.injector = injector;
        formStackMap = new HashMap<>();
    }

    public <T extends Form> T createForm(Class<T> formClass) {
        return injector.getInstance(formClass);
    }

    public <T extends Form> void sendForm(Class<T> formClass, Player player) {
        sendForm(createForm(formClass), player);
    }

    public <T extends Form> void sendCopy(T form, Player player) {
        sendForm(createForm(form.getClass()).copy(form), player);
    }

    public <T extends Form> void sendForm(T form, Player player) {
        form.build(player);
        player.showFormWindow((FormWindow) form);
        if (!form.getClass().isAnnotationPresent(IgnoreStack.class)) {
            if (!formStackMap.containsKey(player)) {
                formStackMap.put(player, new Stack<>());
            }
            Stack<Form> formStack = formStackMap.get(player);
            if ((!formStack.empty()) && (formStack.peek().getClass().equals(form.getClass()))) {
                formStack.pop();
            }
            formStack.push(form);
        }
    }

    public void handleResponse(FormWindow form, Player player, boolean wasClosed, FormResponse response) {
        if (form instanceof Form) {
            if ((wasClosed) && (!form.getClass().isAnnotationPresent(IgnoreStack.class))) {
                Stack<Form> formStack = formStackMap.get(player);
                formStack.remove(form);
                if (formStack.empty()) {
                    if (form.getClass().isAnnotationPresent(Required.class)) {
                        ExitForm exitForm = createForm(ExitForm.class);
                        exitForm.setCallback(() -> sendCopy((Form) form, player));
                        sendForm(exitForm, player);
                    }
                } else {
                    sendCopy(formStack.pop(), player);
                }
            } else {
                if (form instanceof SimpleForm) {
                    ((SimpleForm) form).handle(player, wasClosed, (FormResponseSimple) response);
                } else if (form instanceof ModalForm) {
                    ((ModalForm) form).handle(player, wasClosed, (FormResponseModal) response);
                } else if (form instanceof CustomForm) {
                    ((CustomForm) form).handle(player, wasClosed, (FormResponseCustom) response);
                }
            }
        }
    }
}