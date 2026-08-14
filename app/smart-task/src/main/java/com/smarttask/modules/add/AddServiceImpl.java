package com.smarttask.modules.add;

import com.smarttask.core.models.Task;
import com.smarttask.core.repository.TaskRepository;
import com.smarttask.core.repository.TaskRepositoryImpl;

public class AddServiceImpl implements AddService {

    private static final AddServiceImpl INSTANCE = new AddServiceImpl();

    private final TaskRepository taskRepository;

    private AddServiceImpl() {
        this.taskRepository = TaskRepositoryImpl.getInstance();
    }

    public static AddServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void addTask(Task task) {
        this.taskRepository.save(task);
    }

}
