package ru.bestaford.ariovale;

import cn.nukkit.Server;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.scheduler.ServerScheduler;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bestaford.ariovale.listener.AuthenticationListener;
import ru.bestaford.ariovale.listener.FormListener;
import ru.bestaford.ariovale.service.*;

@Log4j2
public final class Core extends PluginBase {

    @Override
    public void onEnable() {
        try {
            Injector injector = Guice.createInjector(Stage.DEVELOPMENT, new Module(this));

            PluginManager pluginManager = getServer().getPluginManager();
            pluginManager.registerEvents(injector.getInstance(AuthenticationListener.class), this);
            pluginManager.registerEvents(injector.getInstance(FormListener.class), this);
        } catch (Throwable throwable) {
            log.fatal("An error occurred during startup", throwable);
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
            Server server = core.getServer();
            bind(Server.class).toInstance(server);
            bind(ServerScheduler.class).toInstance(server.getScheduler());

            //Hibernate
            bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());

            //Services
            bind(AuthenticationService.class).asEagerSingleton();
            bind(FormService.class).asEagerSingleton();
            bind(TaskService.class).asEagerSingleton();
            bind(TranslationService.class).asEagerSingleton();
            bind(UtilsService.class).asEagerSingleton();

            //Listeners
            bind(AuthenticationListener.class).asEagerSingleton();
            bind(FormListener.class).asEagerSingleton();
        }
    }
}