package com.yashraj.skillerpartnerapp.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.yashraj.skillerpartnerapp.EditProfile;
import com.yashraj.skillerpartnerapp.MainActivity;
import com.yashraj.skillerpartnerapp.Model.Vendor;
import com.yashraj.skillerpartnerapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    CircleImageView profileImage;
    TextView userName, userMobile;
    TextView editProfile;
    TextView workHistory;
    TextView shareApp;
    TextView rateApp;
    FirebaseUser mUser;
    FirebaseAuth mAuth;
    Button logout_button;
    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        context = getActivity();
        mAuth = FirebaseAuth.getInstance();
        mUser = FirebaseAuth.getInstance().getCurrentUser();
        userName = view.findViewById(R.id.userName);
        userMobile = view.findViewById(R.id.userMobile);

        ///////Logout Button///////
        logout_button = view.findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_icon)
                        .setTitle("Skiller")
                        .setMessage("Do you really want to logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logoutUser();
                            }
                        }).setNegativeButton("No", null).show();


            }
        });

        //////Profile Image///////
        profileImage = view.findViewById(R.id.user_profile_image);


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
        showUserDetails();

        return view;
    }

    private void showUserDetails() {
        FirebaseDatabase.getInstance().getReference().child("Vendors").child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Vendor vendor = snapshot.getValue(Vendor.class);
                if (vendor != null) {
                    userName.setText(vendor.getName());
                    userMobile.setText(vendor.getPhoneNo().substring(3));
                    Picasso.get().load(vendor.getImageUrl()).placeholder(R.drawable.ic_baseline_account_circle_24).into(profileImage);
                } else {
                    userName.setVisibility(View.GONE);
                    userMobile.setVisibility(View.GONE);
                    Picasso.get().load(R.drawable.ic_icon).into(profileImage);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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