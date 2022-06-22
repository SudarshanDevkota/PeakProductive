package com.example.peakproductive;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.peakproductive.models.TaskModel;
import com.example.peakproductive.repo.MainRepository;

public class EditTaskDetailsActivity extends AppCompatActivity {
    TextView titleTV,taskTV;
    Button btnAdd;
    MainRepository repo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_details);
        titleTV = findViewById(R.id.edit_task_title);
        btnAdd = findViewById(R.id.btn_addUpdate_task);
        btnAdd.setOnClickListener(addResponse);
        repo = new MainRepository(this);


    }

    View.OnClickListener addResponse = v->{
        String task = titleTV.getText().toString();
        if(!task.isEmpty()){
            TaskModel model = new TaskModel();
            model.setDescription(task);
            repo.addTask(model);
            finish();
        }
        else{
            Toast.makeText(this,"Empty Task",Toast.LENGTH_SHORT).show();
        }

    };
}