package ru.bestaford.ariovale.module;

import cn.nukkit.utils.Config;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import ru.bestaford.ariovale.Core;
import ru.bestaford.ariovale.impl.AuthorizationServiceImpl;
import ru.bestaford.ariovale.impl.FormServiceImpl;
import ru.bestaford.ariovale.impl.TranslationServiceImpl;
import ru.bestaford.ariovale.service.AuthorizationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

import java.sql.SQLException;

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
        return core.getConfig();
    }

    @Provides
    @Singleton
    public ConnectionSource provideConnectionSource(Config config) {
        try {
            return new JdbcPooledConnectionSource(
                    config.getString("database.url"),
                    config.getString("database.username"),
                    config.getString("database.password")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}