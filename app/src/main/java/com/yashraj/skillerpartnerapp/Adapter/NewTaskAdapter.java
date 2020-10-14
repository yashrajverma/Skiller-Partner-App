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
import com.yashraj.skillerpartnerapp.R;

import java.util.List;

public class NewTaskAdapter extends RecyclerView.Adapter<NewTaskAdapter.ViewHolder> {
    private Context mContext;
    private List<NewTask> mNewTaskList;
    private FirebaseUser firebaseUser;

    public NewTaskAdapter(Context mContext, List<NewTask> mNewTaskList) {
        this.mContext = mContext;
        this.mNewTaskList = mNewTaskList;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_new_task, parent, false);
        return new NewTaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewTask task = mNewTaskList.get(position);
        holder.description.setText(task.getDescription());
        holder.location.setText(task.getLocation());
        holder.phoneNumber.setText(task.getPhoneNo());
        holder.charges.setText(task.getCharges());
        holder.duration.setText(task.getDuration());


    }

    @Override
    public int getItemCount() {
        return mNewTaskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public TextView location;
        public TextView phoneNumber;
        public TextView call;
        public TextView charges;
        public TextView duration;
        public Button acceptButton;
        public Button declineButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.new_task_desc);
            location = itemView.findViewById(R.id.location);
            phoneNumber = itemView.findViewById(R.id.phoneNumber);
            call = itemView.findViewById(R.id.call_now_btn);
            charges = itemView.findViewById(R.id.charges);
            duration = itemView.findViewById(R.id.duration);
            acceptButton = itemView.findViewById(R.id.accept_btn);
            declineButton = itemView.findViewById(R.id.decline_btn);

        }
    }
}
