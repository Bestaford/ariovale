package ru.bestaford.ariovale.service;

import cn.nukkit.Player;
import ru.bestaford.ariovale.form.InformationForm;
import ru.bestaford.ariovale.util.Strings;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class UtilsService {

    @Inject private TranslationService translationService;
    @Inject private FormService formService;

    public void information(Player player, String key, Object... args) {
        formService.sendForm(new InformationForm(translationService.getString(player, key, args)), player);
    }

    public void closeWithError(Player player) {
        String message = Strings.THEME_ERROR + translationService.getString(player, "error.text");
        player.close(message, message);
    }
}