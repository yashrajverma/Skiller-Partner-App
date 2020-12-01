package com.yashraj.skillerpartnerapp.RegistrationActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.yashraj.skillerpartnerapp.DashBoardActivity;
import com.yashraj.skillerpartnerapp.R;

import java.util.concurrent.TimeUnit;

public class UserLoginActivity extends AppCompatActivity {
    EditText otp;
    TextView phone;
    Button login;
    String phoneNumber;
    String codeBySystem;
    FirebaseAuth mAuth;


    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeBySystem = s;
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            signInWithPhoneAuthCredential(phoneAuthCredential);

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(UserLoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        login = findViewById(R.id.btn_login);
        otp = findViewById(R.id.otp);
        phone = findViewById(R.id.phoneNumber);
        mAuth = FirebaseAuth.getInstance();
        phoneNumber = getIntent().getStringExtra("phone");
        phone.setText(phoneNumber);

        sendOTP();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otp.getText().toString().isEmpty()) {
                    Toast.makeText(UserLoginActivity.this, "Please Enter your OTP", Toast.LENGTH_SHORT).show();
                } else if (otp.getText().toString().length() != 6) {
                    Toast.makeText(UserLoginActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                } else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, otp.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////
    private void sendOTP() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,                         //phoneNumber to verify
                60,                            //Timeout Duration
                TimeUnit.SECONDS,                //Unit of timeout
                this,      //Activity(for callback binding)
                mCallbacks);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    sendUserToMainActivity();

                } else {
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(UserLoginActivity.this, "Some Error occurred", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    private void sendUserToMainActivity() {
        Intent intent = new Intent(UserLoginActivity.this, DashBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


}