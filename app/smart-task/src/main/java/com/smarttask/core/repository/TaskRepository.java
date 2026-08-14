package com.smarttask.core.repository;

import java.util.List;

import com.smarttask.core.models.Task;

public interface TaskRepository {

    public void save(Task task);

    public List<Task> findAll();

    public Task findById(int id);

    public void update(Task task);

    public void delete(int id);

}
