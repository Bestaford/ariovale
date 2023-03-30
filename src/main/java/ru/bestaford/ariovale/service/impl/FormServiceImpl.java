package ru.bestaford.ariovale.service.impl;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import com.google.inject.Injector;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Form;
import ru.bestaford.ariovale.form.base.ModalForm;
import ru.bestaford.ariovale.form.base.SimpleForm;
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
    public <T extends Form> void sendForm(T form, Player player) {
        injector.injectMembers(form);
        form.build();
        player.showFormWindow((FormWindow) form);
    }

    @Override
    public void handleResponse(FormWindow window, Player player, boolean wasClosed, FormResponse response) {
        if (window instanceof SimpleForm) {
            ((SimpleForm) window).handle(player, wasClosed, (FormResponseSimple) response);
        } else if (window instanceof ModalForm) {
            ((ModalForm) window).handle(player, wasClosed, (FormResponseModal) response);
        } else if (window instanceof CustomForm) {
            ((CustomForm) window).handle(player, wasClosed, (FormResponseCustom) response);
        }
    }
}