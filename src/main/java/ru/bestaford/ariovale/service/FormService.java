package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import com.google.inject.Injector;
import lombok.extern.log4j.Log4j2;
import ru.bestaford.ariovale.form.ExitForm;
import ru.bestaford.ariovale.form.base.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
@Singleton
public final class FormService {

    private final Map<FormWindow, Form> windowMap = new ConcurrentHashMap<>();
    private final Map<Player, Stack<Form>> formStackMap = new ConcurrentHashMap<>();

    @Inject private Injector injector;
    @Inject private UtilsService utilsService;

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
            if (!formStack.contains(form)) {
                formStack.push(form);
            }
        }
    }

    public void clearStack(Player player) {
        if (formStackMap.containsKey(player)) {
            Stack<Form> formStack = formStackMap.get(player);
            formStack.clear();
        }
    }

    public void handleResponse(FormWindow window, Player player, boolean wasClosed, FormResponse response) {
        if (windowMap.containsKey(window)) {
            try {
                Form form = windowMap.get(window);
                if (!form.getClass().isAnnotationPresent(IgnoreStack.class) && wasClosed) {
                    Stack<Form> formStack = formStackMap.get(player);
                    formStack.remove(form);
                    if (formStack.empty()) {
                        if (form.getClass().isAnnotationPresent(Required.class)) {
                            sendForm(new ExitForm(() -> sendForm(form, player)), player);
                        }
                    } else {
                        sendForm(formStack.pop(), player);
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
            } catch (Exception exception) {
                log.error("An error occurred during form handling", exception);
                utilsService.closeWithError(player);
            } finally {
                windowMap.remove(window);
            }
        }
    }
}