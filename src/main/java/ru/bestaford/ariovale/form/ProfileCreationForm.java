package ru.bestaford.ariovale.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementLabel;
import cn.nukkit.form.element.ElementSlider;
import cn.nukkit.form.response.FormResponseCustom;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.entity.ProfileData;
import ru.bestaford.ariovale.form.base.CustomForm;
import ru.bestaford.ariovale.form.base.Required;
import ru.bestaford.ariovale.manager.AuthenticationManager;
import ru.bestaford.ariovale.manager.FormManager;
import ru.bestaford.ariovale.manager.TranslationManager;
import ru.bestaford.ariovale.util.Sex;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

@Required
public final class ProfileCreationForm extends CustomForm {

    public final Account account;

    @Inject private TranslationManager translationManager;
    @Inject private FormManager formManager;
    @Inject private AuthenticationManager authenticationManager;

    public ProfileCreationForm(Account account) {
        Preconditions.checkArgument(account != null);
        this.account = account;
    }

    @Override
    protected void build(Player player) {
        window.setTitle(FORMAT_BOLD + PORTAL_NAME + ": " + translationManager.getString(player, "profile.creation.form.title") + " " + REGISTRATION_STAGE_2);
        window.addElement(new ElementLabel(translationManager.getString(player, "profile.creation.form.label", THEME_OOC)));
        window.addElement(new ElementLabel(translationManager.getString(player, "profile.creation.form.name", THEME_PRIMARY + account.getName())));
        window.addElement(new ElementDropdown(
                translationManager.getString(player, "sex.title"),
                Arrays.stream(Sex.values()).map(
                        sex -> translationManager.getString(player, "sex." + sex.toString().toLowerCase())
                ).collect(Collectors.toList()),
                Sex.OTHER.ordinal()
        ));
        window.addElement(new ElementSlider(
                translationManager.getString(player, "age"),
                Account.MIN_AGE,
                Account.MAX_AGE,
                1,
                Account.MIN_AGE
        ));
    }

    @Override
    public void handle(Player player, boolean wasClosed, FormResponseCustom response) {
        Preconditions.checkArgument(player != null && !wasClosed && response != null);
        ProfileData profileData = new ProfileData();
        profileData.setSex(Sex.values()[response.getDropdownResponse(2).getElementID()]);
        profileData.setAge((int) response.getSliderResponse(3));
        profileData.setMoney(BigDecimal.ZERO);
        account.setProfileData(profileData);
        formManager.clearStack(player);
        authenticationManager.register(player, account);
    }
}