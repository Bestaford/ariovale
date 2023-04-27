package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.element.ElementSlider;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;
import ru.bestaford.ariovale.util.Sex;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Required
public final class ProfileCreationForm extends CustomForm {

    public final Account account;

    @Inject private TranslationService translationService;
    @Inject private FormService formService;
    @Inject private AuthenticationService authenticationService;

    public ProfileCreationForm(Account account) {
        this.account = Objects.requireNonNull(account);
    }

    @Override
    protected void build(Player player) {
        window.setTitle(FORMAT_BOLD + PORTAL_NAME + COLON + SPACE + translationService.getString(player, "profile.creation.form.title") + SPACE + REGISTRATION_STAGE_2);
        window.addElement(new ElementLabel(translationService.getString(player, "profile.creation.form.label", THEME_OOC)));
        window.addElement(new ElementLabel(translationService.getString(player, "profile.creation.form.name", THEME_PRIMARY + account.getName())));
        window.addElement(new ElementDropdown(
                translationService.getString(player, "sex.title"),
                Arrays.stream(Sex.values()).map(
                        sex -> translationService.getString(player, "sex." + sex.toString().toLowerCase())
                ).collect(Collectors.toList()),
                account.getSex() == null ? Sex.OTHER.ordinal() : account.getSex().ordinal()
        ));
        window.addElement(new ElementSlider(
                translationService.getString(player, "age"),
                Account.MIN_AGE,
                Account.MAX_AGE,
                1,
                Math.max(account.getAge(), Account.MIN_AGE)
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        account.setSex(Sex.values()[response.getDropdownResponse(2).getElementID()]);
        account.setAge((int) response.getSliderResponse(3));
        formService.clearStack(player);
        authenticationService.register(player, account);
    }
}