package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.element.ElementSlider;
import cn.nukkit.form.response.FormResponseCustom;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Form;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.TranslationService;
import ru.bestaford.ariovale.util.Sex;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.stream.Collectors;

@Required
public final class ProfileCreationForm extends CustomForm {

    private final transient AuthenticationService authenticationService;
    private transient Account account;

    @Inject
    public ProfileCreationForm(TranslationService translationService, AuthenticationService authenticationService) {
        super(translationService);
        this.authenticationService = authenticationService;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void build(Player player) {
        setTitle(translationService.getString("profile.creation.form.title", player) + " [2/2]");
        addElement(new ElementLabel(translationService.getString("profile.creation.form.label", player)));
        addElement(new ElementLabel(
                translationService.getString("profile.creation.form.name", player) + ": " + account.getName()
        ));
        addElement(new ElementDropdown(
                translationService.getString("sex.title", player),
                Arrays.stream(Sex.values()).map(
                        sex -> translationService.getString("sex." + sex.toString().toLowerCase(), player)
                ).collect(Collectors.toList()),
                account.getSex() == null ? 0 : account.getSex().ordinal()
        ));
        addElement(new ElementSlider(
                translationService.getString("age", player),
                Account.MIN_AGE,
                Account.MAX_AGE,
                1,
                Math.max(account.getAge(), Account.MIN_AGE)
        ));
    }

    @Override
    public Form copy(Form other) {
        if (other instanceof ProfileCreationForm form) {
            account = form.account;
        }
        return this;
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        account.setSex(Sex.values()[response.getDropdownResponse(2).getElementID()]);
        account.setAge((int) response.getSliderResponse(3));
        authenticationService.register(player, account);
    }
}