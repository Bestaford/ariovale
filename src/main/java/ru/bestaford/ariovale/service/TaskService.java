package ru.bestaford.ariovale.service;

import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.scheduler.ServerScheduler;
import com.google.inject.Injector;
import ru.bestaford.ariovale.Core;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class TaskService {

    @Inject private Injector injector;
    @Inject private Core core;
    @Inject private ServerScheduler scheduler;

    public void scheduleAsyncTask(AsyncTask task) {
        injector.injectMembers(task);
        scheduler.scheduleAsyncTask(core, task);
    }
}