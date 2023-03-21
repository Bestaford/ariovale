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

    private Injector injector;

    @Override
    public void onEnable() {
        initialize();
        registerListeners();
    }

    private void initialize() {
        saveDefaultConfig();
        injector = Guice.createInjector(new CoreModule(this));
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