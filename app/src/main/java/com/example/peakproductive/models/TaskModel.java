package com.example.peakproductive.models;

import java.io.Serializable;

public class TaskModel implements Serializable {

    private int taskId;
    private String taskDescription;
    private boolean isCompleted;
    private String taskCatagory;






    public TaskModel(int taskId, String taskDescription ,String taskCatagory,  boolean isCompleted) {
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.isCompleted = isCompleted;
        this.taskCatagory = taskCatagory;

    }

    public String getTaskCatagory() {
        return taskCatagory;
    }

    public void setTaskCatagory(String taskCatagory) {
        this.taskCatagory = taskCatagory;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
