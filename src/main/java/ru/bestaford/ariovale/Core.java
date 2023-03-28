package ru.bestaford.ariovale;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.utils.Logger;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bestaford.ariovale.listener.AuthorizationListener;
import ru.bestaford.ariovale.listener.FormListener;
import ru.bestaford.ariovale.listener.impl.AuthorizationListenerImpl;
import ru.bestaford.ariovale.listener.impl.FormListenerImpl;
import ru.bestaford.ariovale.service.AuthorizationService;
import ru.bestaford.ariovale.service.FormService;
import ru.bestaford.ariovale.service.TranslationService;
import ru.bestaford.ariovale.service.impl.AuthorizationServiceImpl;
import ru.bestaford.ariovale.service.impl.FormServiceImpl;
import ru.bestaford.ariovale.service.impl.TranslationServiceImpl;

public final class Core extends PluginBase {

    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(Stage.DEVELOPMENT, new Module(this));

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(injector.getInstance(AuthorizationListener.class), this);
        pluginManager.registerEvents(injector.getInstance(FormListener.class), this);
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

            //Hibernate
            bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());

            //Services
            bind(AuthorizationService.class).to(AuthorizationServiceImpl.class).asEagerSingleton();
            bind(FormService.class).to(FormServiceImpl.class).asEagerSingleton();
            bind(TranslationService.class).to(TranslationServiceImpl.class).asEagerSingleton();

            //Listeners
            bind(AuthorizationListener.class).to(AuthorizationListenerImpl.class).asEagerSingleton();
            bind(FormListener.class).to(FormListenerImpl.class).asEagerSingleton();
        }
    }
}