package ru.bestaford.ariovale.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import ru.bestaford.ariovale.Core;
import ru.bestaford.ariovale.impl.AuthorizationServiceImpl;
import ru.bestaford.ariovale.impl.CoreServiceImpl;
import ru.bestaford.ariovale.impl.FormServiceImpl;
import ru.bestaford.ariovale.impl.TranslationServiceImpl;
import ru.bestaford.ariovale.service.AuthorizationService;
import ru.bestaford.ariovale.service.CoreService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

public final class CoreModule extends AbstractModule {

    private final Core core;

    public CoreModule(Core core) {
        this.core = core;
    }

    @Override
    protected void configure() {
        bind(AuthorizationService.class).to(AuthorizationServiceImpl.class);
        bind(CoreService.class).to(CoreServiceImpl.class);
        bind(FormService.class).to(FormServiceImpl.class);
        bind(TranslationService.class).to(TranslationServiceImpl.class);
    }

    @Provides
    public Core provideCore() {
        return core;
    }
}