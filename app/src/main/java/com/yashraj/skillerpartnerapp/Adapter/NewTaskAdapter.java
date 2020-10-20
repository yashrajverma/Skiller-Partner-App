package com.yashraj.skillerpartnerapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.yashraj.skillerpartnerapp.Model.NewTask;
import com.yashraj.skillerpartnerapp.Model.Task;
import com.yashraj.skillerpartnerapp.R;

import java.util.ArrayList;
import java.util.List;

public class NewTaskAdapter extends RecyclerView.Adapter<NewTaskAdapter.ViewHolder> {
    private Context mContext;
    private List<Task> mNewTaskList=new ArrayList<>();
    FirebaseUser firebaseUser;

    public NewTaskAdapter(Context mContext, List<Task> mNewTaskList) {
        this.mContext = mContext;
        this.mNewTaskList = mNewTaskList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_new_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final Task task = mNewTaskList.get(position);
        holder.description.setText(task.getUser_desc());
        holder.location.setText(task.getUser_address());
        holder.phoneNumber.setText(task.getUser_mobile());
        holder.charges.setText(task.getUser_duration());
        holder.duration.setText(task.getUser_duration());


    }

    @Override
    public int getItemCount() {
        return mNewTaskList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private View view;
        private TextView location;
        private TextView phoneNumber;
        private TextView call;
        private TextView charges;
        private TextView duration;
        private Button acceptButton;
        private Button declineButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.new_task_desc);
            view = itemView.findViewById(R.id.view_2);
            location = itemView.findViewById(R.id.new_task_location);
            phoneNumber = itemView.findViewById(R.id.new_task_phone_number);
            call = itemView.findViewById(R.id.call_now_btn);
            charges = itemView.findViewById(R.id.new_task_charges);
            duration = itemView.findViewById(R.id.new_task_duration);
            acceptButton = itemView.findViewById(R.id.accept_btn);
            declineButton = itemView.findViewById(R.id.decline_btn);

        }
    }
}
