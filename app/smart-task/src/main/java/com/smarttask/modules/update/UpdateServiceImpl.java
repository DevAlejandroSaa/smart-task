package com.smarttask.modules.update;

import com.smarttask.core.exception.exceptions.TaskNotFoundException;
import com.smarttask.core.models.Priority;
import com.smarttask.core.models.Status;
import com.smarttask.core.models.Task;
import com.smarttask.core.repository.TaskRepository;
import com.smarttask.core.repository.TaskRepositoryImpl;
import com.smarttask.core.resources.MessageResources;

public class UpdateServiceImpl implements UpdateService {

    private static final UpdateServiceImpl INSTANCE = new UpdateServiceImpl();

    private final TaskRepository taskRepository;
    private final MessageResources messageResources;

    private UpdateServiceImpl() {
        this.taskRepository = TaskRepositoryImpl.getInstance();
        this.messageResources = MessageResources.getInstance();
    }

    public static UpdateServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void updateTask(int id, Priority priority, Status status) {
        Task task = this.taskRepository.findById(id);

        if (task == null) {
            throw new TaskNotFoundException(this.messageResources.getMessage("error.task.not.found"));
        }

        task.setPriority(priority);
        task.setStatus(status);

        this.taskRepository.update(task);
    }

}
