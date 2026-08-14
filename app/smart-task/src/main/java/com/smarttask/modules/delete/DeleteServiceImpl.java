package com.smarttask.modules.delete;

import com.smarttask.core.exception.exceptions.TaskNotFoundException;
import com.smarttask.core.models.Task;
import com.smarttask.core.repository.TaskRepository;
import com.smarttask.core.repository.TaskRepositoryImpl;
import com.smarttask.core.resources.MessageResources;

public class DeleteServiceImpl implements DeleteService {

    private static final DeleteServiceImpl INSTANCE = new DeleteServiceImpl();

    private final TaskRepository taskRepository;
    private final MessageResources messageResources;

    private DeleteServiceImpl() {
        this.taskRepository = TaskRepositoryImpl.getInstance();
        this.messageResources = MessageResources.getInstance();
    }

    public static DeleteServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void deleteTask(int id) {
        Task task = this.taskRepository.findById(id);

        if (task == null) {
            throw new TaskNotFoundException(this.messageResources.getMessage("error.task.not.found"));
        }

        this.taskRepository.delete(id);
    }

}
