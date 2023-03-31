package ru.bestaford.ariovale.service;

import ru.bestaford.ariovale.task.Task;

public interface TaskService {

    <T extends Task> T createTask(Class<T> taskClass);

    <T extends Task> void scheduleAsyncTask(T task);

}