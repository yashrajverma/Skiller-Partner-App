package com.yashraj.skillerpartnerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yashraj.skillerpartnerapp.Model.OngoingTask;
import com.yashraj.skillerpartnerapp.R;

import java.util.ArrayList;

public class OngoingTaskAdapter extends RecyclerView.Adapter<OngoingTaskAdapter.ViewHolder> {
    private ArrayList<OngoingTask> dataHolder;
    private Context mContext;

    public OngoingTaskAdapter(ArrayList<OngoingTask> dataHolder, Context mContext) {
        this.dataHolder = dataHolder;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_ongoing_task, parent, false);
        return new OngoingTaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OngoingTask task = dataHolder.get(position);
        holder.ongoingTaskLocation.setText(task.getLocation());
        holder.startingDate.setText(task.getStarting_date());

    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text_1;
        public View view;
        public TextView ongoingTaskLocation;
        public TextView text_2;
        public TextView startingDate;
        public Button markCompleteBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_1 = itemView.findViewById(R.id.text_1);
            view = itemView.findViewById(R.id.view);
            ongoingTaskLocation = itemView.findViewById(R.id.ongoing_task_location);
            text_2 = itemView.findViewById(R.id.started_on);
            startingDate = itemView.findViewById(R.id.starting_date);
            markCompleteBtn = itemView.findViewById(R.id.mark_as_complete_btn);
        }
    }
}
