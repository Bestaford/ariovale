package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.service.TranslationService;

import javax.inject.Inject;

public final class ProfileCreationForm extends CustomForm {

    private transient Account account;

    @Inject
    public ProfileCreationForm(TranslationService translationService) {
        super(translationService);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void build(Player player) {
        setTitle(translationService.getString("profile.creation.form.title", player) + " [2/2]");
        addElement(new ElementLabel(
                translationService.getString("profile.creation.form.label", player) + ": " + account.getName()
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {

    }
}