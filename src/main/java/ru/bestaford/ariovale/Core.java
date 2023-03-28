package ru.bestaford.ariovale;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import ru.bestaford.ariovale.listener.AuthorizationListener;
import ru.bestaford.ariovale.listener.FormListener;
import ru.bestaford.ariovale.module.CoreModule;

public final class Core extends PluginBase {

    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(Stage.DEVELOPMENT, new CoreModule(this));

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(injector.getInstance(AuthorizationListener.class), this);
        pluginManager.registerEvents(injector.getInstance(FormListener.class), this);
    }
}