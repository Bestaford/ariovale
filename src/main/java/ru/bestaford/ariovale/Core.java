package ru.bestaford.ariovale;

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import ru.bestaford.ariovale.listener.AuthorizationListener;
import ru.bestaford.ariovale.listener.FormListener;
import ru.bestaford.ariovale.module.CoreModule;
import ru.bestaford.ariovale.module.HibernateModule;

import java.util.ArrayList;

public final class Core extends PluginBase {

    private Injector injector;

    @Override
    public void onEnable() {
        initialize();
        registerListeners();
    }

    private void initialize() {
        injector = Guice.createInjector(
                Stage.DEVELOPMENT,
                new CoreModule(),
                new HibernateModule()
        );
    }

    private void registerListeners() {
        ArrayList<Class<? extends Listener>> listeners = new ArrayList<>();

        listeners.add(AuthorizationListener.class);
        listeners.add(FormListener.class);

        PluginManager pluginManager = getServer().getPluginManager();
        for (Class<? extends Listener> listener : listeners) {
            pluginManager.registerEvents(injector.getInstance(listener), this);
        }
    }
}