package ru.bestaford.ariovale.service;

import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.scheduler.ServerScheduler;
import com.google.inject.Injector;
import ru.bestaford.ariovale.Bootstrapper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class TaskService {

    @Inject private Injector injector;
    @Inject private Bootstrapper bootstrapper;
    @Inject private ServerScheduler scheduler;

    public void scheduleAsyncTask(AsyncTask task) {
        injector.injectMembers(task);
        scheduler.scheduleAsyncTask(bootstrapper, task);
    }
}