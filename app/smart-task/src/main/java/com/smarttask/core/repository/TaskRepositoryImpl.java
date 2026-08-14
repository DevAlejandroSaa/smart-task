package com.smarttask.core.repository;

import java.util.List;

import com.smarttask.core.database.DataStore;
import com.smarttask.core.exception.exceptions.TaskAlreadyExistsException;
import com.smarttask.core.models.Task;
import com.smarttask.core.resources.MessageResources;

public class TaskRepositoryImpl implements TaskRepository {

    private static final TaskRepositoryImpl INSTANCE = new TaskRepositoryImpl();

    private DataStore dataStore;

    private final MessageResources messageResources;

    public TaskRepositoryImpl() {
        this.dataStore = DataStore.getInstance();
        this.messageResources = MessageResources.getInstance();
    }

    public static TaskRepositoryImpl getInstance() {
        return INSTANCE;
    }

    private int generateId() {
        return this.dataStore.getNextId();
    }

    @Override
    public void save(Task task) {
        boolean existsTask = this.existsTaskByName(task.getName());

        if (existsTask) {
            throw new TaskAlreadyExistsException(this.messageResources.getMessage("error.task.already.exists"));
        }

        task.setId(this.generateId());
        this.dataStore.addTask(task);
    }

    private boolean existsTaskByName(String name) {
        return this.dataStore.existsTaskByName(name);
    }

    @Override
    public List<Task> findAll() {
        return this.dataStore.getTasks();
    }

    @Override
    public Task findById(int id) {
        return this.dataStore.getTaskById(id);
    }

    @Override
    public void update(Task task) {
        this.dataStore.updateTask(task);
    }

    @Override
    public void delete(int id) {
        this.dataStore.delete(id);
    }

}
