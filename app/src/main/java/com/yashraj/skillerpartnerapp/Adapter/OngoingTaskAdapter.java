package com.yashraj.skillerpartnerapp.Adapter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yashraj.skillerpartnerapp.Model.OngoingTask;
import com.yashraj.skillerpartnerapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

public class OngoingTaskAdapter extends RecyclerView.Adapter<OngoingTaskAdapter.ViewHolder> {
    private final ArrayList<OngoingTask> dataHolder;
    private final Context mContext;
    private final FirebaseUser mUser;
    boolean clicked = false;
    private DatabaseReference reference;

    public OngoingTaskAdapter(ArrayList<OngoingTask> dataHolder, Context mContext) {
        this.dataHolder = dataHolder;
        this.mContext = mContext;
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference().child("OngoingTask").child(mUser.getUid()).child("Ongoing");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_ongoing_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final OngoingTask task = dataHolder.get(position);

        holder.ongoingTaskLocation.setText(task.getLocation());
        holder.startingDate.setText(task.getStartingDate());
        holder.dateSelectorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Please select ending date", Toast.LENGTH_SHORT).show();
                selectEndingDate(task.getWorkId());
                holder.markCompleteBtn.setText("Selected");
                clicked = true;

            }
        });
        holder.markCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked) {
                    addCompletedTask(task.getWorkId(), task.getStartingDate(), task.getEndingDate(), task.getLocation(), task.getVendorId(), task.getCharges(), task.getVendorName(), task.getDescription());
                    holder.markCompleteBtn.setText("Completed");
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            reference.child(task.getWorkId()).child("completed").setValue("Yes");
                        }
                    }, 2000);


                } else {
                    Toast.makeText(mContext, "Please Select date first", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    private void selectEndingDate(final String workId) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        int mYear = cal.get(Calendar.YEAR);
        int mMonth = cal.get(Calendar.MONTH);
        int mDate = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, android.R.style.ThemeOverlay_Material_Dialog_Alert, new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String endingDate = dayOfMonth + "-" + (month + 1) + "-" + year;
                reference.child(workId).child("endingDate").setValue(endingDate);


            }
        }, mYear, mMonth, mDate);
        datePickerDialog.show();
    }

    private void addCompletedTask(String workId, String startingDate, String endingDate, String location, String vendorId, int charges, String vendorName, String description) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("location", location);
        map.put("startingDate", startingDate);
        map.put("endingDate", endingDate);
        map.put("workId", workId);
        map.put("vendorId", vendorId);
        map.put("charges", charges);
        map.put("vendorName", vendorName);
        map.put("description", description);

        FirebaseDatabase.getInstance().getReference().child("CompletedTask").child(mUser.getUid()).child("Completed").child(workId).setValue(map);
    }


    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text_1;
        public View view;
        public TextView ongoingTaskLocation;
        public TextView text_2;
        public TextView startingDate;
        public Button markCompleteBtn;
        public Button dateSelectorBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_1 = itemView.findViewById(R.id.text_1);
            view = itemView.findViewById(R.id.view);
            ongoingTaskLocation = itemView.findViewById(R.id.ongoing_task_location);
            text_2 = itemView.findViewById(R.id.started_on);
            startingDate = itemView.findViewById(R.id.starting_date);
            markCompleteBtn = itemView.findViewById(R.id.mark_as_complete_btn);
            dateSelectorBtn = itemView.findViewById(R.id.choose_date);
        }
    }
}
