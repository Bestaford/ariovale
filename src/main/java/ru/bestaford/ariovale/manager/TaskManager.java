package ru.bestaford.ariovale.manager;

import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import ru.bestaford.ariovale.Bootstrapper;

@Singleton
public final class TaskManager {

    @Inject private Injector injector;
    @Inject private Bootstrapper bootstrapper;

    public void scheduleAsyncTask(AsyncTask task) {
        injector.injectMembers(task);
        Server.getInstance().getScheduler().scheduleAsyncTask(bootstrapper, task);
    }
}