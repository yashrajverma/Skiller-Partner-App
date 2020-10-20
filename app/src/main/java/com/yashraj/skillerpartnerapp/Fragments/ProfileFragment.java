package com.yashraj.skillerpartnerapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.yashraj.skillerpartnerapp.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {
    CircleImageView profileImage;
    TextView editProfile;
    View line;
    TextView workHistory;
    TextView paymentHistory;
    TextView shareApp;
    Button logoutBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        getActivity().getActionBar().hide();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        profileImage = view.findViewById(R.id.profile_image);
        editProfile = view.findViewById(R.id.edit_profile);
        line = view.findViewById(R.id.single_line);
        workHistory = view.findViewById(R.id.work_history);
        paymentHistory = view.findViewById(R.id.payment_history);
        shareApp = view.findViewById(R.id.share_app);
        logoutBtn = view.findViewById(R.id.logout_btn);
        return view;
    }
}