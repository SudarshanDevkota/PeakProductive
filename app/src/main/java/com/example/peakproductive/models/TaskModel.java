package com.example.peakproductive.models;

import androidx.room.Entity;

@Entity
public class TaskModel{

    private int taskId;
    private String taskDescription;
    private boolean isCompleted;
    private int priority;


    public TaskModel(int taskId, String taskDescription, boolean isCompleted, int priority) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.isCompleted = isCompleted;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
