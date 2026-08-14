package com.smarttask.modules.search;

import java.util.List;

import com.smarttask.core.models.Task;
import com.smarttask.core.repository.TaskRepository;
import com.smarttask.core.repository.TaskRepositoryImpl;

public class SearchServiceImpl implements SearchService {

    private static final SearchServiceImpl INSTANCE = new SearchServiceImpl();

    private final TaskRepository taskRepository;

    private SearchServiceImpl() {
        this.taskRepository = TaskRepositoryImpl.getInstance();
    }

    public static SearchServiceImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Task> listTask() {
        return this.taskRepository.findAll();
    }

}
