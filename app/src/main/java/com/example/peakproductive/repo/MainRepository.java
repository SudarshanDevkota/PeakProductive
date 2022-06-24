package com.example.peakproductive.repo;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.peakproductive.dao.FlashCardDao;
import com.example.peakproductive.dao.TaskDao;
import com.example.peakproductive.db.LocalDatabase;
import com.example.peakproductive.models.CardModel;
import com.example.peakproductive.models.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class MainRepository {

    LocalDatabase localDatabase;
    Context context;
    TaskDao taskDao;
    FlashCardDao flashCardDao;

    public MainRepository(Context context){
        this.context = context;
        localDatabase = LocalDatabase.getInstance(context);
        taskDao = localDatabase.getTaskDao();
        flashCardDao = localDatabase.getFlashCardDao();

    }
    public LiveData<List<TaskModel>> getAllTask(){
        return taskDao.getAllTasks();
    }
    public void addTask(TaskModel task){
        taskDao.insert(task);
        Log.d("TAG", "addTask: added"+getAllTask());
    }
    public void deleteTask(TaskModel taskModel){
            taskDao.delete(taskModel);
    }
    public void updateTask(TaskModel task){
        taskDao.update(task);
    }
    public void addFlashCard(CardModel model){
        flashCardDao.insert(model);
    }
    public void updateFlashCard(CardModel model){
        flashCardDao.update(model);
    }
    public LiveData<List<CardModel>> getAllFlashCards(){
        return flashCardDao.getAllCards();
    }
    public void deleteFlashCard(CardModel model){
        flashCardDao.delete(model);
    }

}
