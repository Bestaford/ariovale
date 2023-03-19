package ru.bestaford.ariovale.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import ru.bestaford.ariovale.Core;

public final class CoreModule extends AbstractModule {

    private final Core core;

    public CoreModule(Core core) {
        this.core = core;
    }

    @Provides
    public Core provideCore() {
        return core;
    }
}