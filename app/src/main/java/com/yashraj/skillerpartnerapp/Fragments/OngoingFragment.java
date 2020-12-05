package com.yashraj.skillerpartnerapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yashraj.skillerpartnerapp.Adapter.OngoingTaskAdapter;
import com.yashraj.skillerpartnerapp.Model.OngoingTask;
import com.yashraj.skillerpartnerapp.R;

import java.util.ArrayList;


public class OngoingFragment extends Fragment {
    TextView noTasks;
    ProgressBar pb;
    RecyclerView onGoingTask;
    ArrayList<OngoingTask> ongoingTaskList;
    OngoingTaskAdapter taskAdapter;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_ongoing, container, false);
        mAuth = FirebaseAuth.getInstance();
        pb = view.findViewById(R.id.progressBar);
        onGoingTask = view.findViewById(R.id.ongoing_task);
        noTasks = view.findViewById(R.id.no_ongoingTask_text);
        onGoingTask.setLayoutManager(new LinearLayoutManager(getContext()));
        ongoingTaskList = new ArrayList<>();
        taskAdapter = new OngoingTaskAdapter(ongoingTaskList, getContext());
        onGoingTask.setAdapter(taskAdapter);
        readOngoingTasks();

        return view;
    }

    /////// To fetch details in CardView from the firebase ///////
    private void readOngoingTasks() {
        pb.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance().getReference().child("OngoingTask").child(mAuth.getCurrentUser().getUid()).child("Ongoing")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        noTasks.setVisibility(View.VISIBLE);
                        ongoingTaskList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            OngoingTask task = dataSnapshot.getValue(OngoingTask.class);
                            assert task != null;
                            if (task.getCompleted().equals("Yes")) {
                                ongoingTaskList.remove(task);
                            } else {
                                ongoingTaskList.add(task);
                            }
                            if (!ongoingTaskList.isEmpty()) {
                                noTasks.setVisibility(View.GONE);
                            } else {
                                noTasks.setVisibility(View.VISIBLE);
                            }
                        }
                        pb.setVisibility(View.GONE);
                        taskAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}