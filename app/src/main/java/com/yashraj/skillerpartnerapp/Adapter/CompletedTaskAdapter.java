package com.yashraj.skillerpartnerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yashraj.skillerpartnerapp.Model.CompletedTask;
import com.yashraj.skillerpartnerapp.R;

import java.util.ArrayList;

public class CompletedTaskAdapter extends RecyclerView.Adapter<CompletedTaskAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<CompletedTask> cTaskList;

    public CompletedTaskAdapter(Context mContext, ArrayList<CompletedTask> cTaskList) {
        this.mContext = mContext;
        this.cTaskList = cTaskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_completed_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CompletedTask task = cTaskList.get(position);
        holder.cLocation.setText(task.getLocation());
        holder.cStartingDate.setText(task.getStartingDate());
        holder.cEndingDate.setText(task.getEndingDate());
        holder.cAmount.setText(task.getPayment());

    }

    @Override
    public int getItemCount() {
        return cTaskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cLocation;
        public TextView cStartedOn;
        public TextView cStartingDate;
        public TextView cEndOn;
        public TextView cEndingDate;
        public TextView cPayment;
        public TextView cAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cLocation = itemView.findViewById(R.id.c_location);
            cStartedOn = itemView.findViewById(R.id.started_on);
            cStartingDate = itemView.findViewById(R.id.c_starting_date);
            cEndOn = itemView.findViewById(R.id.c_ended_on);
            cEndingDate = itemView.findViewById(R.id.c_ending_date);
            cPayment = itemView.findViewById(R.id.c_payment_received);
            cAmount = itemView.findViewById(R.id.c_amount);
        }
    }
}
