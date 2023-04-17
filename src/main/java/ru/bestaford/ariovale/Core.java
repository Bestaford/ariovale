package ru.bestaford.ariovale;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.scheduler.ServerScheduler;
import cn.nukkit.utils.Logger;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bestaford.ariovale.listener.AuthenticationListener;
import ru.bestaford.ariovale.listener.FormListener;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;

public final class Core extends PluginBase {

    @Override
    public void onEnable() {
        try {
            Injector injector = Guice.createInjector(Stage.DEVELOPMENT, new Module(this));

            PluginManager pluginManager = getServer().getPluginManager();
            pluginManager.registerEvents(injector.getInstance(AuthenticationListener.class), this);
            pluginManager.registerEvents(injector.getInstance(FormListener.class), this);
        } catch (Exception e) {
            getLogger().error(e.getMessage());
            System.exit(1);
        }
    }

    private static final class Module extends AbstractModule {

        private final Core core;

        public Module(Core core) {
            this.core = core;
        }

        @Override
        protected void configure() {
            //Core
            bind(Core.class).toInstance(core);
            bind(Logger.class).toInstance(core.getLogger());
            bind(Server.class).toInstance(core.getServer());
            bind(ServerScheduler.class).toInstance(core.getServer().getScheduler());

            //Hibernate
            bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());

            //Services
            bind(AuthenticationService.class).asEagerSingleton();
            bind(FormService.class).asEagerSingleton();
            bind(TranslationService.class).asEagerSingleton();

            //Listeners
            bind(AuthenticationListener.class).asEagerSingleton();
            bind(FormListener.class).asEagerSingleton();
        }
    }
}