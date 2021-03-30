package com.example.peakproductive.adaptors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.peakproductive.R;
import com.example.peakproductive.models.TaskModel;
import java.util.ArrayList;

public class TaskModelAdaptor extends RecyclerView.Adapter<TaskModelAdaptor.ViewHolder> {

    private static ArrayList<TaskModel> taskList;
    CheckBoxListener checkBoxListener;

    private Context context;



    public TaskModelAdaptor(Context context, ArrayList<TaskModel> taskList , CheckBoxListener listener) {
        this.checkBoxListener = listener;
        this.taskList = taskList;
        this.context= context;

    }
    public Context getContext(){
        return context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_view_model,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.checkBox.setChecked(taskList.get(position).isCompleted());
        holder.taskDeskcripton.setText(taskList.get(position).getTaskDescription());
        holder.taskCatagory.setText(taskList.get(position).getTaskCatagory());



    }

    @Override
    public int getItemCount() {
        return taskList.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public TextView taskDeskcripton,taskCatagory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check_box);
            taskDeskcripton = itemView.findViewById(R.id.task_content);
            taskCatagory = itemView.findViewById(R.id.edit_catagory);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkBoxListener.onCheckBoxClicked(getAdapterPosition());
                }
            });

        }


    }
    public interface CheckBoxListener{
        void onCheckBoxClicked(int position);
    }

}
