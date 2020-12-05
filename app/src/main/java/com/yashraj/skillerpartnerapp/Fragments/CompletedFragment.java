package com.yashraj.skillerpartnerapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yashraj.skillerpartnerapp.Adapter.CompletedTaskAdapter;
import com.yashraj.skillerpartnerapp.Model.CompletedTask;
import com.yashraj.skillerpartnerapp.R;

import java.util.ArrayList;


public class CompletedFragment extends Fragment {
    TextView textView;
    RecyclerView cTaskView;
    ArrayList<CompletedTask> cTaskList;
    CompletedTaskAdapter taskAdapter;
    FirebaseUser mUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_completed, container, false);
        cTaskView = view.findViewById(R.id.completed_task);
        textView = view.findViewById(R.id.c_textView);
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        cTaskView.setLayoutManager(new LinearLayoutManager(getContext()));
        cTaskList = new ArrayList<>();
        taskAdapter = new CompletedTaskAdapter(getContext(), cTaskList);
        cTaskView.setAdapter(taskAdapter);
        readCompletedTasks();
        return view;

    }

    ///////// To fetch details from the Catdview from the firebase //////
    private void readCompletedTasks() {
        FirebaseDatabase.getInstance().getReference().child("CompletedTask").child(mUser.getUid()).child("Completed")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        textView.setVisibility(View.VISIBLE);
                        cTaskList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            CompletedTask task = dataSnapshot.getValue(CompletedTask.class);
                            cTaskList.add(task);
                            if (!cTaskList.isEmpty()) {
                                textView.setVisibility(View.GONE);
                            }
                        }
                        taskAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}