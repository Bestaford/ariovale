package ru.bestaford.ariovale.module.provider;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.inject.Provider;

public class SessionFactoryProvider implements Provider<SessionFactory> {

    @Override
    public SessionFactory get() {
        return new Configuration().configure().buildSessionFactory();
    }
}