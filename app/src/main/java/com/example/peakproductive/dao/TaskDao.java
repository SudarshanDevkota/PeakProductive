package com.example.peakproductive.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.peakproductive.models.TaskModel;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(TaskModel taskModel);

    @Query("SELECT * FROM task_table")
    List<TaskModel> getAllTasks();


}
