package ru.bestaford.ariovale.module;

import com.google.inject.AbstractModule;
import ru.bestaford.ariovale.listener.AuthorizationListener;
import ru.bestaford.ariovale.listener.FormListener;
import ru.bestaford.ariovale.listener.impl.AuthorizationListenerImpl;
import ru.bestaford.ariovale.listener.impl.FormListenerImpl;
import ru.bestaford.ariovale.service.AuthorizationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;
import ru.bestaford.ariovale.service.impl.AuthorizationServiceImpl;
import ru.bestaford.ariovale.service.impl.FormServiceImpl;
import ru.bestaford.ariovale.service.impl.TranslationServiceImpl;

public final class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AuthorizationListener.class).to(AuthorizationListenerImpl.class).asEagerSingleton();
        bind(FormListener.class).to(FormListenerImpl.class).asEagerSingleton();
        bind(AuthorizationService.class).to(AuthorizationServiceImpl.class).asEagerSingleton();
        bind(FormService.class).to(FormServiceImpl.class).asEagerSingleton();
        bind(TranslationService.class).to(TranslationServiceImpl.class).asEagerSingleton();
    }
}