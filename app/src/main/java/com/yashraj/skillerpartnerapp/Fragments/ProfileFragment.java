package com.yashraj.skillerpartnerapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.yashraj.skillerpartnerapp.MainActivity;
import com.yashraj.skillerpartnerapp.R;
import android.app.Activity;

public class ProfileFragment extends Fragment {
    FirebaseAuth mAuth;
    private Button logout_button;
    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        context=getActivity();
        mAuth=FirebaseAuth.getInstance();
        logout_button=view.findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void logoutUser(){
        mAuth.signOut();
        Intent intent=new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        context.finish();
    }
}