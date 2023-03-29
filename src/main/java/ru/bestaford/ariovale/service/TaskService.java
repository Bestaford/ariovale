package ru.bestaford.ariovale.service;

import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.scheduler.TaskHandler;

public interface TaskService {

    TaskHandler scheduleAsyncTask(AsyncTask task);

}