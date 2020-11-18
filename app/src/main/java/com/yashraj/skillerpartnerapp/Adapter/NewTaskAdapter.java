package com.yashraj.skillerpartnerapp.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yashraj.skillerpartnerapp.Model.NewTask;
import com.yashraj.skillerpartnerapp.Model.Vendor;
import com.yashraj.skillerpartnerapp.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.O)
public class NewTaskAdapter extends RecyclerView.Adapter<NewTaskAdapter.ViewHolder> {
    Context mContext;
    ArrayList<NewTask> mNewTaskList;
    FirebaseUser firebaseUser;


    ////// To Get current date ///////////
    LocalDate dateObj = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String date = dateObj.format(formatter);

    public NewTaskAdapter(Context mContext, ArrayList<NewTask> mNewTaskList) {
        this.mContext = mContext;
        this.mNewTaskList = mNewTaskList;

    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_new_task, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final NewTask task = mNewTaskList.get(position);

        holder.description.setText(task.getDescription());
        holder.location.setText(task.getLocation());
        holder.phoneNumber.setText(task.getPhoneNo());
        holder.charges.setText(task.getCharges() + " Rs/day");
        holder.duration.setText(task.getDuration() + " days");
        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext).setMessage("Are you sure to accept this work?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ///// To change the value of accepted to 'Yes' ///////
                        FirebaseDatabase.getInstance().getReference().child("NewTask").child(firebaseUser.getUid()).child("Tasks").child(task.getWorkId()).child("accepted").setValue("yes");
                        ///// to get user's location ////////
                        String location = task.getAddress() + "," + task.getLocation();
                        addOngoingTask(firebaseUser.getUid(), task.getWorkId(), location, date, "No", task.getDescription(), task.getCharges());
                        ////// to get vendor's name //////////
                        getVendorsData(task.getWorkId());
                        Toast.makeText(mContext, "Task Accepted successfully", Toast.LENGTH_SHORT).show();
                        holder.acceptButton.setText("Accepted");


                    }
                }).setNegativeButton("No", null).show();

            }
        });


    }

    ///////Method to get Vendor's data ///////////
    private void getVendorsData(final String workId) {
        FirebaseDatabase.getInstance().getReference().child("Vendors").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Vendor vendor = snapshot.getValue(Vendor.class);

                FirebaseDatabase.getInstance().getReference().child("OngoingTask").child(firebaseUser.getUid()).child("Ongoing").child(workId).child("vendorName").setValue(vendor.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /////// Method to send data in the Ongoing task ///////////////
    private void addOngoingTask(String vendorId, String workId, String location, String date, String completed, String description, long vendorCharges) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("workId", workId);
        map.put("vendorId", vendorId);
        map.put("location", location);
        map.put("startingDate", date);
        map.put("completed", completed);
        map.put("description", description);
        map.put("charges", vendorCharges);

        FirebaseDatabase.getInstance().getReference().child("OngoingTask").child(firebaseUser.getUid()).child("Ongoing").child(workId).setValue(map);
    }


    @Override
    public int getItemCount() {
        return mNewTaskList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        View view;
        TextView location;
        TextView phoneNumber;
        TextView call;
        TextView charges;
        TextView duration;
        Button acceptButton;
        Button declineButton;

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
