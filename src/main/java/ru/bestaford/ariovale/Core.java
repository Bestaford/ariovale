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
import ru.bestaford.ariovale.listener.impl.AuthenticationListenerImpl;
import ru.bestaford.ariovale.listener.impl.FormListenerImpl;
import ru.bestaford.ariovale.service.AuthenticationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TaskService;
import ru.bestaford.ariovale.service.TranslationService;
import ru.bestaford.ariovale.service.impl.AuthenticationServiceImpl;
import ru.bestaford.ariovale.service.impl.FormServiceImpl;
import ru.bestaford.ariovale.service.impl.TaskServiceImpl;
import ru.bestaford.ariovale.service.impl.TranslationServiceImpl;
import ru.bestaford.ariovale.util.Utils;

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
            bind(AuthenticationService.class).to(AuthenticationServiceImpl.class).asEagerSingleton();
            bind(FormService.class).to(FormServiceImpl.class).asEagerSingleton();
            bind(TaskService.class).to(TaskServiceImpl.class).asEagerSingleton();
            bind(TranslationService.class).to(TranslationServiceImpl.class).asEagerSingleton();

            //Listeners
            bind(AuthenticationListener.class).to(AuthenticationListenerImpl.class).asEagerSingleton();
            bind(FormListener.class).to(FormListenerImpl.class).asEagerSingleton();

            //Misc
            bind(Utils.class);
        }
    }
}