package com.yashraj.skillerpartnerapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yashraj.skillerpartnerapp.Adapter.NewTaskAdapter;
import com.yashraj.skillerpartnerapp.Model.NewTask;
import com.yashraj.skillerpartnerapp.R;

import java.util.ArrayList;
import java.util.List;


public class NewFragment extends Fragment {
    private RecyclerView newTaskRecyclerView;
    private NewTaskAdapter newTaskAdapter;
    private List<NewTask> taskList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        newTaskRecyclerView = view.findViewById(R.id.new_task);
        newTaskRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        newTaskRecyclerView.setLayoutManager(linearLayoutManager);
        taskList = new ArrayList<>();
        newTaskAdapter = new NewTaskAdapter(getContext(), taskList);
        newTaskRecyclerView.setAdapter(newTaskAdapter);


        return view;
    }
}