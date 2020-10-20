package com.yashraj.skillerpartnerapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yashraj.skillerpartnerapp.Adapter.CompletedTaskAdapter;
import com.yashraj.skillerpartnerapp.Model.CompletedTask;
import com.yashraj.skillerpartnerapp.R;

import java.util.ArrayList;


public class CompletedFragment extends Fragment {
    RecyclerView cTaskView;
    ArrayList<CompletedTask> cTaskList;
    CompletedTaskAdapter taskAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_completed, container, false);
        cTaskView = view.findViewById(R.id.completed_task);
        cTaskView.setLayoutManager(new LinearLayoutManager(getContext()));
        cTaskList = new ArrayList<>();
        taskAdapter = new CompletedTaskAdapter(getContext(), cTaskList);
        CompletedTask obj1 = new CompletedTask("Indra Vihar, Agra", "20-oct-2020", "25-oct-2020", "Rs.3000");
        cTaskList.add(obj1);
        CompletedTask obj2 = new CompletedTask("Fatehabad,Agra", "26-oct-2020", "30-0ct-2020", "Rs.2500");
        cTaskList.add(obj2);
        cTaskView.setAdapter(taskAdapter);
        return view;

    }
}