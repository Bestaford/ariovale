package ru.bestaford.ariovale.module;

import com.google.inject.AbstractModule;
import ru.bestaford.ariovale.service.AuthorizationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;
import ru.bestaford.ariovale.service.impl.AuthorizationServiceImpl;
import ru.bestaford.ariovale.service.impl.FormServiceImpl;
import ru.bestaford.ariovale.service.impl.TranslationServiceImpl;

public final class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AuthorizationService.class).to(AuthorizationServiceImpl.class);
        bind(FormService.class).to(FormServiceImpl.class);
        bind(TranslationService.class).to(TranslationServiceImpl.class);
    }
}