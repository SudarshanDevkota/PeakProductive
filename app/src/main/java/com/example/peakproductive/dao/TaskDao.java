package com.example.peakproductive.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.peakproductive.models.TaskModel;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(TaskModel taskModel);

    @Query("SELECT * FROM task_table")
    LiveData<List<TaskModel>> getAllTasks();

    @Update
    void update(TaskModel taskModel);

    @Delete
    void delete(TaskModel taskModel);


}
