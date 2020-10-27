package com.yashraj.skillerpartnerapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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

    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new, container, false);

        newTaskRecyclerView = view.findViewById(R.id.new_task);
        taskList = new ArrayList<>();
        newTaskAdapter = new NewTaskAdapter(getContext(), taskList);
        newTaskRecyclerView.setAdapter(newTaskAdapter);
        newTaskRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        readTasks();

        return view;
    }


    private void readTasks() {
        FirebaseDatabase.getInstance().getReference().child("NewTask").child(mAuth.getCurrentUser().getUid()).child("Tasks")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        taskList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            NewTask task = dataSnapshot.getValue(NewTask.class);
                            assert task != null;
                            if (task.getAccepted().equals("yes")) {
                                taskList.remove(task);
                            } else {
                                taskList.add(task);
                            }

                        }
                        newTaskAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }


}


//    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseRecyclerOptions<Task> options=new FirebaseRecyclerOptions.Builder<Task>().setQuery(reference,Task.class).build();
//        firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Task, TaskViewHolder>(options) {
//
//            @Override
//            protected void onBindViewHolder(@NonNull TaskViewHolder holder, int i, @NonNull Task task) {
//                holder.desc_txtView.setText(task.getUser_desc());
//                holder.phoneNumber_txtView.setText(task.getUser_mobile());
//                holder.location_txtView.setText(task.getUser_address());
//                holder.duration_txtView.setText(task.getUser_duration());
//                holder.charges_txtView.setText("300");
//
//            }
//
//            @NonNull
//            @Override
//            public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view=LayoutInflater.from(getContext()).inflate(R.layout.adapter_new_task,parent,false);
//                return new TaskViewHolder(view);
//            }
//        };
//        firebaseRecyclerAdapter.startListening();
//        newTaskRecyclerView.setAdapter(firebaseRecyclerAdapter);
//
//    }
//
//
//    private static class TaskViewHolder extends RecyclerView.ViewHolder{
//        Button accept_btn,decline_btn;
//        private TextView location_txtView,charges_txtView,duration_txtView,desc_txtView,call_now_textview,phoneNumber_txtView;
//
//        public TaskViewHolder(View itemView) {
//            super(itemView);
//
//            location_txtView=itemView.findViewById(R.id.new_task_location);
//            charges_txtView=itemView.findViewById(R.id.new_task_charges);
//            duration_txtView=itemView.findViewById(R.id.new_task_duration);
//            desc_txtView=itemView.findViewById(R.id.new_task_desc);
//            call_now_textview=itemView.findViewById(R.id.call_now_btn);
//            phoneNumber_txtView=itemView.findViewById(R.id.new_task_phone_number);
//            accept_btn=itemView.findViewById(R.id.accept_btn);
//            decline_btn=itemView.findViewById(R.id.decline_btn);
//
//            accept_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    acceptWork();
//                }
//            });
//
//            decline_btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    declineWork();
//                }
//            });
//
//        }
//    }
//
//    private static void acceptWork(){
//
//    }
//    private static void declineWork(){
//
//    }

