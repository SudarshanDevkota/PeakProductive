package com.example.peakproductive;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peakproductive.models.TaskModel;
import com.example.peakproductive.repo.MainRepository;

public class EditTaskDetailsActivity extends AppCompatActivity {
    EditText titleEV;
    Button btnAdd;
    Spinner priority;
    MainRepository repo;
    TaskModel model;
    boolean isUpdate;
    String[] priorities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_details);
        titleEV = findViewById(R.id.edit_task_title);
        btnAdd = findViewById(R.id.btn_addUpdate_task);
        btnAdd.setOnClickListener(addResponse);
        priority = findViewById(R.id.spinner);
        priorities = new String[] {"High","Moderate","Low"};
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,priorities);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        priority.setAdapter(adapter);
        priority.setSelection(1);
        repo = new MainRepository(this);
        if(getIntent().getStringExtra("type").equals("edit")){
            isUpdate = true;
            model = (TaskModel) getIntent().getSerializableExtra("model");
            Log.d("object details", "onCreate: " +model.getId());
            titleEV.setText(model.getDescription());
            btnAdd.setText(getResources().getString(R.string.update_text));



        }


    }

    View.OnClickListener addResponse = v->{
        String task = titleEV.getText().toString();
        if(!task.isEmpty()){
            if(model==null){
                model = new TaskModel();
            }
            model.setDescription(task);
            if(isUpdate){
                repo.updateTask(model);
            }
            model.setPriority(priority.getSelectedItemPosition());
            repo.addTask(model);
            finish();
        }
        else{
            Toast.makeText(this,"Empty Task",Toast.LENGTH_SHORT).show();
        }

    };

}