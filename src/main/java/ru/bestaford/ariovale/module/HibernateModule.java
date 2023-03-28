package ru.bestaford.ariovale.module;

import com.google.inject.AbstractModule;
import org.hibernate.SessionFactory;
import ru.bestaford.ariovale.module.provider.SessionFactoryProvider;

public final class HibernateModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SessionFactory.class).toProvider(SessionFactoryProvider.class).asEagerSingleton();
    }
}