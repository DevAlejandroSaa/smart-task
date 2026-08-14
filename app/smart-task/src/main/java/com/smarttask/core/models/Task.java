package com.smarttask.core.models;

public class Task {
    private int id;
    private String name;
    private Priority priority;
    private Status status;

    public Task(String name, Priority priority) {
        this.name = name;
        this.priority = priority;
        this.status = Status.ACTIVA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", name=" + name + ", priority=" + priority + ", status=" + status + "]";
    }

}
