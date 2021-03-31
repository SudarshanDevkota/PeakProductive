package com.example.peakproductive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.example.peakproductive.fragments.TaskDetailsFragment;

import com.example.peakproductive.models.TaskModel;

public class EditTaskDetailsActivity extends AppCompatActivity {

    private static final String TASK_ACTIVITY_ARG = "com.example.peakproductive.task_activity_arg";
    private static int current = 0;
    Fragment fragment;


    public static Intent TaskDetailActivityIntent(Context context) {
        Intent intent = new Intent(context, EditTaskDetailsActivity.class);
        current = 0;
        return intent;
    }

    public static Intent TaskDetailActivityIntent(Context context, TaskModel model) {
        Intent intent = new Intent(context, EditTaskDetailsActivity.class);
        intent.putExtra(TASK_ACTIVITY_ARG, model);
        current = 1;
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_details);
        if (current == 0) {

            fragment = TaskDetailsFragment.getNewInstance();
        } else {
            TaskModel task = (TaskModel) getIntent().getSerializableExtra(TASK_ACTIVITY_ARG);
            fragment = TaskDetailsFragment.getNewInstance(task);
        }


        getSupportFragmentManager().beginTransaction().add(R.id.task_detail_container, fragment).commit();


    }
}