package com.yashraj.skillerpartnerapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yashraj.skillerpartnerapp.Fragments.CompletedFragment;
import com.yashraj.skillerpartnerapp.Fragments.NewFragment;
import com.yashraj.skillerpartnerapp.Fragments.OngoingFragment;
import com.yashraj.skillerpartnerapp.Fragments.ProfileFragment;

public class DashBoardActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new NewFragment();
                    break;
                case R.id.nav_ongoing:
                    selectedFragment = new OngoingFragment();
                    break;
                case R.id.nav_completed:
                    selectedFragment = new CompletedFragment();
                    break;
                case R.id.nav_profile:
                    selectedFragment = new ProfileFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewFragment()).commit();

    }

}