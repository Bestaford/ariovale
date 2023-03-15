package ru.bestaford.ariovale;

import cn.nukkit.plugin.PluginBase;
import ru.bestaford.ariovale.annotation.EntryPoint;

@EntryPoint
public final class Ariovale extends PluginBase {

    @Override
    public void onEnable() {
        getLogger().info("Hello world!");
    }
}