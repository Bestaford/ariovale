package ru.bestaford.ariovale.module;

import cn.nukkit.utils.Config;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public final class DatabaseModule extends AbstractModule {

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