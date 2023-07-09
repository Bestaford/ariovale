package ru.bestaford.ariovale.manager;

import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import com.google.inject.Injector;
import ru.bestaford.ariovale.Ariovale;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class TaskManager {

    @Inject private Injector injector;
    @Inject private Ariovale plugin;

    public void scheduleAsyncTask(AsyncTask task) {
        injector.injectMembers(task);
        Server.getInstance().getScheduler().scheduleAsyncTask(plugin, task);
    }
}