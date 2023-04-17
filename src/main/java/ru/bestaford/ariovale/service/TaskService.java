package ru.bestaford.ariovale.service;

import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.scheduler.ServerScheduler;
import com.google.inject.Injector;
import ru.bestaford.ariovale.Core;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class TaskService {

    private final Injector injector;
    private final Core core;
    private final ServerScheduler scheduler;

    @Inject
    public TaskService(Injector injector, Core core, ServerScheduler scheduler) {
        this.injector = injector;
        this.core = core;
        this.scheduler = scheduler;
    }

    public void scheduleAsyncTask(AsyncTask task) {
        injector.injectMembers(task);
        scheduler.scheduleAsyncTask(core, task);
    }
}