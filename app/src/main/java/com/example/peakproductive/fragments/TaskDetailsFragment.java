package com.example.peakproductive.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peakproductive.R;



import com.example.peakproductive.models.TaskModel;

import com.example.peakproductive.utils.TaskFactory;


public class TaskDetailsFragment extends Fragment {
    private static final String TASK_FRAGMENT_ARG= "com.example.peakproductive.fragments.task_fragment_arg";

    private EditText title , catagoty;
    private Button saveUpdateButton;
    private static int current=0;
    private TaskModel taskModel;
    private TaskFactory factory;

    public static TaskDetailsFragment getNewInstance(TaskModel model) {
        Bundle args = new Bundle();
        current = 1;
        args.putSerializable(TASK_FRAGMENT_ARG,model);

        TaskDetailsFragment fragment = new TaskDetailsFragment();
        fragment.setArguments(args);
        return fragment;

    }
    public static TaskDetailsFragment getNewInstance() {
        current =0;
        TaskDetailsFragment taskDetailsFragment = new TaskDetailsFragment();
        return  taskDetailsFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_details, container, false);
        title = view.findViewById(R.id.edit_task_title);
        catagoty = view.findViewById(R.id.edit_taskCatagory);
        saveUpdateButton = view.findViewById(R.id.btn_addUpdate_task);
        saveUpdateButton.setOnClickListener(saveUpdate);
        factory = new TaskFactory(getActivity());

        return view;
    
    }
    View.OnClickListener saveUpdate = v ->{

        String heading = title.getText().toString();

        String topic = catagoty.getText().toString();


        if (!heading.isEmpty()) {
            if(current == 0){
                factory.addTask(heading, topic,0);
                Toast.makeText(getActivity(), "Additoon Successful", Toast.LENGTH_SHORT).show();

            }else{
                factory.updateTask(taskModel.getTaskId(),heading,topic,taskModel.isCompleted()?1:0);
            }
            getActivity().finish();


        } else
            Toast.makeText(getActivity(), "Cannot Add Empty Task", Toast.LENGTH_SHORT).show();


    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(current ==1){
            taskModel = (TaskModel) getArguments().getSerializable(TASK_FRAGMENT_ARG);
            title.setText(taskModel.getTaskDescription());
            catagoty.setText(taskModel.getTaskCatagory());
            saveUpdateButton.setText(R.string.update_text);
        }
    }
}