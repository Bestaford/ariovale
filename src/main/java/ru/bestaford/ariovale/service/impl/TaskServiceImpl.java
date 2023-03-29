package ru.bestaford.ariovale.service.impl;

import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.scheduler.ServerScheduler;
import cn.nukkit.scheduler.TaskHandler;
import com.google.inject.Injector;
import ru.bestaford.ariovale.Core;
import ru.bestaford.ariovale.service.TaskService;

import javax.inject.Inject;

public final class TaskServiceImpl implements TaskService {

    private final Injector injector;
    private final Core core;
    private final ServerScheduler scheduler;

    @Inject
    public TaskServiceImpl(Injector injector, Core core, ServerScheduler scheduler) {
        this.injector = injector;
        this.core = core;
        this.scheduler = scheduler;
    }

    @Override
    public TaskHandler scheduleAsyncTask(AsyncTask task) {
        injector.injectMembers(task);
        return scheduler.scheduleAsyncTask(core, task);
    }
}