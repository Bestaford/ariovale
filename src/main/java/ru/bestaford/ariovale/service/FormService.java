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

    public final Map<FormWindow, Form> formMap = new ConcurrentHashMap<>();
    public final Map<Player, Stack<Form>> stackMap = new ConcurrentHashMap<>();

    @Inject private Injector injector;
    @Inject private UtilsService utilsService;

    public void sendForm(Form form, Player player) {
        sendForm(form, player, false);
    }

    public void sendForm(Form form, Player player, boolean silent) {
        injector.injectMembers(form);
        FormWindow window = form.getWindow(player); //TODO: move in condition block
        if (!silent) {
            player.showFormWindow(window);
            formMap.put(window, form);
        }
        if (!form.getClass().isAnnotationPresent(IgnoreStack.class)) {
            Stack<Form> formStack = stackMap.get(player);
            if (!formStack.contains(form)) {
                formStack.push(form);
            }
        }
    }

    public void clearStack(Player player) {
        stackMap.get(player).clear();
    }

    public void handleResponse(FormWindow window, Player player, boolean wasClosed, FormResponse response) {
        if (formMap.containsKey(window)) {
            try {
                Form form = formMap.get(window);
                if (!form.getClass().isAnnotationPresent(IgnoreStack.class) && wasClosed) {
                    Stack<Form> formStack = stackMap.get(player);
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
                formMap.remove(window);
            }
        }
    }
}
