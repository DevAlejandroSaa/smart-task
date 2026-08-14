package com.smarttask.core.database;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

import com.smarttask.core.models.Task;

public class DataStore {

    private static DataStore instance;

    private List<Task> tasks;
    private PriorityQueue<Integer> freeIds;

    private DataStore() {
        tasks = new ArrayList<Task>();
        this.freeIds = new PriorityQueue<>();
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }

        return instance;
    }

    public int getNextId() {
        if (!this.freeIds.isEmpty()) {
            return this.freeIds.poll() + 1;
        }

        return this.tasks.size() + 1;
    }

    public void addTask(Task task) {
        int index = task.getId() - 1;

        if (index < this.tasks.size()) {
            this.tasks.set(index, task);
        } else {
            this.tasks.add(task);
        }
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public Task getTaskById(int id) {
        return id - 1 >= this.tasks.size() ? null : this.tasks.get(id - 1);
    }

    public void updateTask(Task task) {
        this.tasks.set(task.getId() - 1, task);
    }

    public void delete(int id) {
        this.tasks.set(id - 1, null);
        this.registerFreePosition(id);
    }

    public void registerFreePosition(int id) {
        this.tasks.set(id - 1, null);
        this.cleanTrailingNulls();
        this.verifyAndRegisterNulls();
        this.validateAndCleanFreeIds();
    }

    private void cleanTrailingNulls() {
        while (!this.tasks.isEmpty() && this.tasks.get(this.tasks.size() - 1) == null) {
            this.tasks.remove(this.tasks.size() - 1);
        }
    }

    private void verifyAndRegisterNulls() {
        IntStream.range(0, this.tasks.size())
                .filter(i -> this.tasks.get(i) == null)
                .forEach(i -> {
                    if (!this.freeIds.contains(i)) {
                        this.freeIds.offer(i);
                    }
                });
    }

    private void validateAndCleanFreeIds() {
        this.freeIds.removeIf(id -> this.tasks.get(id) != null);
    }

    public boolean existsTaskByName(String name) {
        return this.tasks.stream()
                .filter(task -> task != null)
                .anyMatch(task -> task.getName().equalsIgnoreCase(name));
    }
}
