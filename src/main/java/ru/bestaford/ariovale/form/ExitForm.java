package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class ExitForm extends ConfirmationForm {

    private final transient FormService formService;
    private transient Form callbackForm;

    @Inject
    public ExitForm(TranslationService translationService, FormService formService) {
        super(translationService);
        this.formService = formService;
    }

    public Form getCallbackForm() {
        return callbackForm;
    }

    public void setCallbackForm(Form callbackForm) {
        this.callbackForm = callbackForm;
    }

    @Override
    public void build(Player player) {
        super.build(player);
        setTitle(translationService.getString("exit.form.title", player));
        setContent(translationService.getString("exit.form.content", player));
    }

    @Override
    public void handle(Player player, boolean confirmed) {
        if (confirmed) {
            String message = translationService.getString("exit.text", player);
            player.close(message, message);
        } else {

        }
    }
}