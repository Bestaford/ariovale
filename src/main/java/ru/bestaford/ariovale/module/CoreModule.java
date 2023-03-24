package ru.bestaford.ariovale.module;

import cn.nukkit.utils.Config;
import cn.nukkit.utils.Logger;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import ru.bestaford.ariovale.Core;
import ru.bestaford.ariovale.impl.AuthorizationServiceImpl;
import ru.bestaford.ariovale.impl.FormServiceImpl;
import ru.bestaford.ariovale.impl.TranslationServiceImpl;
import ru.bestaford.ariovale.service.AuthorizationService;
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
        bind(FormService.class).to(FormServiceImpl.class);
        bind(TranslationService.class).to(TranslationServiceImpl.class);
    }

    @Provides
    @Singleton
    public Core provideCore() {
        return core;
    }

    @Provides
    @Singleton
    public Config provideConfig(Core core) {
        core.saveDefaultConfig();
        return core.getConfig();
    }

    @Provides
    @Singleton
    public Logger provideLogger(Core core) {
        return core.getLogger();
    }
}