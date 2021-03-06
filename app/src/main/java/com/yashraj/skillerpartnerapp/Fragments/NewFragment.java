package com.yashraj.skillerpartnerapp.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yashraj.skillerpartnerapp.Adapter.NewTaskAdapter;
import com.yashraj.skillerpartnerapp.Model.NewTask;
import com.yashraj.skillerpartnerapp.R;

import java.util.ArrayList;

public class NewFragment extends Fragment {
    RecyclerView newTaskRecyclerView;
    NewTaskAdapter newTaskAdapter;
    ArrayList<NewTask> taskList;
    TextView textView;
    ProgressBar pb;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        textView = view.findViewById(R.id.no_task_text);
        pb = view.findViewById(R.id.progressBar);
        newTaskRecyclerView = view.findViewById(R.id.new_task);
        taskList = new ArrayList<>();
        newTaskAdapter = new NewTaskAdapter(getContext(), taskList);
        newTaskRecyclerView.setAdapter(newTaskAdapter);
        newTaskRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        textView.setVisibility(View.VISIBLE);


        readTasks();


        return view;
    }

    /////// To Fetch details in CardView from the firebase /////////////
    private void readTasks() {
//        final ProgressDialog pd = new ProgressDialog(getContext());
////        pd.setMessage("Loading...");
//        pd.show();

        pb.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance().getReference().child("NewTask").child(mAuth.getCurrentUser().getUid()).child("Tasks")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        textView.setVisibility(View.VISIBLE);
                        taskList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            NewTask task = dataSnapshot.getValue(NewTask.class);
                            assert task != null;
                            if (taskList.isEmpty()) {
                                textView.setVisibility(View.VISIBLE);
                            } else {
                                textView.setVisibility(View.GONE);
                            }
                            if (task.getAccepted().equals("yes")) {
                                taskList.remove(task);
                            } else {
                                taskList.add(task);
                            }
                            if (!taskList.isEmpty()) {
                                textView.setVisibility(View.GONE);
                            } else {
                                textView.setVisibility(View.VISIBLE);
                            }

                        }
                        newTaskAdapter.notifyDataSetChanged();
                        pb.setVisibility(View.GONE);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


}



