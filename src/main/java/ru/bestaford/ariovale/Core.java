package ru.bestaford.ariovale;

import cn.nukkit.plugin.PluginBase;
import com.google.inject.Guice;
import com.google.inject.Injector;
import ru.bestaford.ariovale.annotation.EntryPoint;
import ru.bestaford.ariovale.module.CoreModule;

@EntryPoint
public final class Core extends PluginBase {

    private final Injector injector;

    public Core() {
        injector = Guice.createInjector(new CoreModule(this));
    }

    @Override
    public void onEnable() {
        getLogger().info(injector.toString());
    }
}