package ru.bestaford.ariovale.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateModule extends AbstractModule {

    @Provides
    @Singleton
    public SessionFactory provideSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }
}