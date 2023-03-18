package ru.bestaford.ariovale;

import cn.nukkit.plugin.PluginBase;
import com.google.inject.Guice;
import com.google.inject.Injector;
import ru.bestaford.ariovale.annotation.EntryPoint;
import ru.bestaford.ariovale.module.CoreModule;

@EntryPoint
public final class Core extends PluginBase {

    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(new CoreModule(this));
    }
}