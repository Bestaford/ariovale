package ru.bestaford.ariovale;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import com.google.inject.Guice;
import com.google.inject.Injector;
import ru.bestaford.ariovale.annotation.EntryPoint;
import ru.bestaford.ariovale.listener.AuthorizationListener;
import ru.bestaford.ariovale.listener.FormListener;
import ru.bestaford.ariovale.module.CoreModule;

import java.util.ArrayList;

@EntryPoint
public final class Core extends PluginBase {

    private final Injector injector;

    public Core() {
        injector = Guice.createInjector(new CoreModule(this));
    }

    @Override
    public void onEnable() {
        registerListeners();
    }

    private void registerListeners() {
        var pluginManager = getServer().getPluginManager();
        var listeners = new ArrayList<Class<? extends Listener>>();

        listeners.add(AuthorizationListener.class);
        listeners.add(FormListener.class);

        for (var listener : listeners) {
            pluginManager.registerEvents(injector.getInstance(listener), this);
        }
    }
}