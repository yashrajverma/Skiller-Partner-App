package com.yashraj.skillerpartnerapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;
import com.yashraj.skillerpartnerapp.EditProfile;
import com.yashraj.skillerpartnerapp.MainActivity;
import com.yashraj.skillerpartnerapp.Model.Vendor;
import com.yashraj.skillerpartnerapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    CircleImageView profileImage;
    TextView editProfile;
    TextView workHistory;
    TextView shareApp;
    TextView rateApp;
    FirebaseAuth mAuth;
    Button logout_button;
    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        context = getActivity();
        mAuth = FirebaseAuth.getInstance();
        Vendor vendor = new Vendor();
        ///////Logout Button///////
        logout_button = view.findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        //////Profile Image///////
        profileImage = view.findViewById(R.id.user_profile_image);
        Picasso.get().load(vendor.getImageUrl()).placeholder(R.drawable.ic_baseline_account_circle_24).into(profileImage);

        //// Edit Profile //////
        editProfile = view.findViewById(R.id.user_profile_edit);
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfile.class));
            }
        });

        ///// Work History /////
        workHistory = view.findViewById(R.id.user_work_history);

        ///// share App ///////
        shareApp = view.findViewById(R.id.user_share);

        ///// rate App ///////
        rateApp = view.findViewById(R.id.user_rate_app);

        return view;
    }

    private void logoutUser() {
        mAuth.signOut();
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        context.finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }
}