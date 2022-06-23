package com.example.peakproductive.adaptors;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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
        int prio = taskList.get(position).getPriority();
        holder.checkBox.setChecked(taskList.get(position).isCompleted());
        holder.taskDescription.setText(taskList.get(position).getDescription());
        holder.checkBox.setOnClickListener(v->{
            listener.onItemClicked(position);

        });
        GradientDrawable bg = (GradientDrawable) holder.layout.getBackground();

        if(prio == 0){
            bg.setStroke(5,getContext().getColor(R.color.orange));
        }else if(prio ==1){
            bg.setStroke(5,getContext().getColor(R.color.green));
        }else{
            bg.setStroke(5,getContext().getColor(R.color.purple_dark));
        }
        if(taskList.get(position).isCompleted()){
            holder.taskDescription.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }


    }

    @Override
    public int getItemCount() {
        return taskList.size();

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public TextView taskDescription;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check_box);
            taskDescription = itemView.findViewById(R.id.task_content);
            layout = itemView.findViewById(R.id.layout);
            }

        }
        public interface CheckboxListener{
            void onItemClicked(int position);
        }


    }



