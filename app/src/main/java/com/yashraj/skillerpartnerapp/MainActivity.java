package com.yashraj.skillerpartnerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;
import com.yashraj.skillerpartnerapp.RegistrationActivities.UserLoginActivity;
import com.yashraj.skillerpartnerapp.RegistrationActivities.UserSignupActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    EditText phoneNo;
    Button getOTP;
    Button test;
    TextView newUser;
    CountryCodePicker ccp;

    ///// Firebase Fields ////////////
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();                   //// To hide Action bar

        ///////// hooks //////////////

        phoneNo = findViewById(R.id.log_phone_number);
        test = findViewById(R.id.btn_skip);
        ccp = findViewById(R.id.ccp);
        getOTP = findViewById(R.id.btn_get_otp);
        newUser = findViewById(R.id.text_new_user);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        ccp.registerCarrierNumberEditText(phoneNo);

        ////// listener to check if user is already logged in //////////

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();
                if (mUser != null) {
                    Toast.makeText(MainActivity.this, "Verified User", Toast.LENGTH_SHORT).show();
                    sendUserToMainActivity();
                    finish();
                }
            }
        };

        ////// Test Button (delete it later) ////////

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DashBoardActivity.class));
                finish();
            }
        });

        ////// New user button ////////

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserSignupActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        ////// Get OTP Button /////////


        getOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserLoginActivity.class);
                intent.putExtra("phone", ccp.getFullNumberWithPlus().trim());
                startActivity(intent);
                finish();

            }
        });


    }

    /////// Method to send user to dashboard Activity ///////

    private void sendUserToMainActivity() {
        Intent intent = new Intent(MainActivity.this, DashBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth != null) {
            mAuth.addAuthStateListener(authStateListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }

}