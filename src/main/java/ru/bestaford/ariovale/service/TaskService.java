package ru.bestaford.ariovale.service;

import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.scheduler.ServerScheduler;
import com.google.inject.Injector;
import ru.bestaford.ariovale.Core;
import ru.bestaford.ariovale.task.Task;

import javax.inject.Inject;

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

    public <T extends Task> T createTask(Class<T> taskClass) {
        return injector.getInstance(taskClass);
    }

    public <T extends Task> void scheduleAsyncTask(T task) {
        scheduler.scheduleAsyncTask(core, (AsyncTask) task);
    }
}