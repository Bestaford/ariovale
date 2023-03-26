package ru.bestaford.ariovale.module;

import com.google.inject.AbstractModule;
import ru.bestaford.ariovale.impl.AuthorizationServiceImpl;
import ru.bestaford.ariovale.impl.FormServiceImpl;
import ru.bestaford.ariovale.impl.TranslationServiceImpl;
import ru.bestaford.ariovale.service.AuthorizationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

public final class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AuthorizationService.class).to(AuthorizationServiceImpl.class);
        bind(FormService.class).to(FormServiceImpl.class);
        bind(TranslationService.class).to(TranslationServiceImpl.class);
    }
}