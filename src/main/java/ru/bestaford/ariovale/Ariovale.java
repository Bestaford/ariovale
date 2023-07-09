package ru.bestaford.ariovale;

import cn.nukkit.Server;
import cn.nukkit.command.SimpleCommandMap;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.PluginManager;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import lombok.extern.log4j.Log4j2;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.io.FileUtils;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.bestaford.ariovale.command.MeCommand;
import ru.bestaford.ariovale.listener.AuthenticationListener;
import ru.bestaford.ariovale.listener.CommandListener;
import ru.bestaford.ariovale.listener.FormListener;
import ru.bestaford.ariovale.util.Strings;
import ru.bestaford.ariovale.util.VoidGenerator;

import java.io.File;
import java.io.IOException;

@Log4j2
public final class Ariovale extends PluginBase {

    @Override
    public void onEnable() {
        try {
            Generator.addGenerator(VoidGenerator.class, "void", Generator.TYPE_INFINITE);
            copyWorld();
            bootstrap();
        } catch (Throwable throwable) {
            log.fatal(Strings.ERROR_STARTUP, throwable);
            System.exit(1);
        }
    }

    private void copyWorld() throws IOException {
        saveResource("world.zip");
        try (ZipFile zipFile = new ZipFile(new File(getDataFolder(), "world.zip"))) {
            File worldsPath = new File(getServer().getDataPath(), "worlds");
            File worldPath = new File(worldsPath, "ariovale");
            if (FileUtils.isDirectory(worldPath)) {
                FileUtils.deleteDirectory(worldPath);
            }
            zipFile.extractAll(worldsPath.getAbsolutePath());
        }
    }

    private void bootstrap() {
        Injector injector = Guice.createInjector(Stage.DEVELOPMENT, new AriovaleModule(this));
        Server server = getServer();
        String fallbackPrefix = "ariovale";

        PluginManager pluginManager = server.getPluginManager();
        pluginManager.registerEvents(injector.getInstance(AuthenticationListener.class), this);
        pluginManager.registerEvents(injector.getInstance(CommandListener.class), this);
        pluginManager.registerEvents(injector.getInstance(FormListener.class), this);

        SimpleCommandMap commandMap = server.getCommandMap();
        commandMap.clearCommands();
        commandMap.register(fallbackPrefix, injector.getInstance(MeCommand.class));
    }

    private static final class AriovaleModule extends AbstractModule {

        private final Ariovale plugin;

        public AriovaleModule(Ariovale plugin) {
            this.plugin = plugin;
        }

        @Override
        protected void configure() {
            bind(Ariovale.class).toInstance(plugin);
            bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());
        }
    }
}