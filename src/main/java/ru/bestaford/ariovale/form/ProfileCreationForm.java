package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.element.ElementSlider;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Form;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;
import ru.bestaford.ariovale.util.Sex;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class ProfileCreationForm extends CustomForm {

    private final transient FormService formService;
    private transient Account account;

    @Inject
    public ProfileCreationForm(TranslationService translationService, FormService formService) {
        super(translationService);
        this.formService = formService;
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
        addElement(new ElementDropdown(
                translationService.getString("sex.title", player),
                Arrays.stream(Sex.values()).map(
                        sex -> translationService.getString("sex." + sex.toString().toLowerCase(), player)
                ).collect(Collectors.toList())
        ));
        addElement(new ElementSlider(
                translationService.getString("age", player),
                (float) Account.MIN_AGE,
                (float) Account.MAX_AGE,
                1
        ));
    }

    @Override
    public Form copy(Form other) {
        return this;
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        if (wasClosed) {
            ExitForm exitForm = formService.createForm(ExitForm.class);
            exitForm.setCallback(() -> {
                ProfileCreationForm profileCreationForm = formService.createForm(ProfileCreationForm.class);
                profileCreationForm.setAccount(account);
                formService.sendForm(profileCreationForm, player);
            });
            formService.sendForm(exitForm, player);
            return;
        }
    }
}