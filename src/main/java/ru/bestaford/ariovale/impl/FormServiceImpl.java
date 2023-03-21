package ru.bestaford.ariovale.impl;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.response.FormResponseSimple;
import com.google.inject.Injector;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Form;
import ru.bestaford.ariovale.form.base.ModalForm;
import ru.bestaford.ariovale.form.base.SimpleForm;
import ru.bestaford.ariovale.service.FormService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public final class FormServiceImpl implements FormService {

    private final Injector injector;
    private final Map<Integer, Form> formCache;

    @Inject
    public FormServiceImpl(Injector injector) {
        this.injector = injector;
        formCache = new ConcurrentHashMap<>();
    }

    @Override
    public <T extends Form> T createForm(Class<T> formClass) {
        return injector.getInstance(formClass);
    }

    @Override
    public <T extends Form> void sendForm(T form, Player player) {
        formCache.put(player.showFormWindow(form.getWindow()), form);
    }

    @Override
    public void handleResponse(int formID, Player player, boolean wasClosed, FormResponse response) {
        if (formCache.containsKey(formID)) {
            Form form = formCache.get(formID);
            if (form instanceof SimpleForm) {
                ((SimpleForm) form).handle(player, wasClosed, (FormResponseSimple) response);
            } else if (form instanceof ModalForm) {
                ((ModalForm) form).handle(player, wasClosed, (FormResponseModal) response);
            } else if (form instanceof CustomForm) {
                ((CustomForm) form).handle(player, wasClosed, (FormResponseCustom) response);
            }
            formCache.remove(formID);
        }
    }
}