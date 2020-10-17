package com.yashraj.skillerpartnerapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yashraj.skillerpartnerapp.Adapter.OngoingTaskAdapter;
import com.yashraj.skillerpartnerapp.Model.OngoingTask;
import com.yashraj.skillerpartnerapp.R;

import java.util.ArrayList;


public class OngoingFragment extends Fragment {

    RecyclerView onGoingTask;
    ArrayList<OngoingTask> dataHolder;
    OngoingTaskAdapter taskAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ongoing, container, false);
        onGoingTask = view.findViewById(R.id.ongoing_task);
        onGoingTask.setLayoutManager(new LinearLayoutManager(getContext()));
        dataHolder = new ArrayList<OngoingTask>();

        OngoingTask ob1 = new OngoingTask("Panchvati,Agra", "23-oct-2020");
        dataHolder.add(ob1);
        OngoingTask ob2 = new OngoingTask("Indrapuram", "15-oct-2020");
        dataHolder.add(ob2);
        taskAdapter = new OngoingTaskAdapter(dataHolder, getContext());
        onGoingTask.setAdapter(taskAdapter);

        return view;
    }
}