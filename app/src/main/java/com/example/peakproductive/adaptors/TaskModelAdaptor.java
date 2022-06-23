package com.example.peakproductive.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peakproductive.R;
import com.example.peakproductive.models.TaskModel;

import java.util.ArrayList;

public class TaskModelAdaptor extends RecyclerView.Adapter<TaskModelAdaptor.ViewHolder> {

    private static ArrayList<TaskModel> taskList;

    private Context context;
    private CheckboxListener listener;


    public TaskModelAdaptor(Context context, ArrayList<TaskModel> taskList,CheckboxListener listener) {
        this.taskList = taskList;
        this.context = context;
        this.listener = listener;

    }

    public Context getContext() {
        return context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_view_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.checkBox.setChecked(taskList.get(position).isCompleted());
        holder.taskDeskcripton.setText(taskList.get(position).getDescription());
//        holder.taskCatagory.setText(taskList.get(position).getPriority());
        holder.checkBox.setOnClickListener(v->{
            listener.onItemClicked(position);

        });


    }

    @Override
    public int getItemCount() {
        return taskList.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public TextView taskDeskcripton, taskCatagory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check_box);
            taskDeskcripton = itemView.findViewById(R.id.task_content);
            taskCatagory = itemView.findViewById(R.id.edit_catagory);

            }

        }
        public interface CheckboxListener{
            void onItemClicked(int position);
        }


    }



