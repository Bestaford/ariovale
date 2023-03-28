package ru.bestaford.ariovale.module;

import com.google.inject.AbstractModule;
import ru.bestaford.ariovale.listener.AuthorizationListener;
import ru.bestaford.ariovale.listener.FormListener;
import ru.bestaford.ariovale.listener.impl.AuthorizationListenerImpl;
import ru.bestaford.ariovale.listener.impl.FormListenerImpl;

public final class ListenerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AuthorizationListener.class).to(AuthorizationListenerImpl.class).asEagerSingleton();
        bind(FormListener.class).to(FormListenerImpl.class).asEagerSingleton();
    }
}