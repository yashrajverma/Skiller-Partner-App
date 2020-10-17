package com.yashraj.skillerpartnerapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.yashraj.skillerpartnerapp.RegistrationActivities.UserSignupActivity;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;
    private TextView newUser;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseUser mUser;
    DatabaseReference databaseReference;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        email = findViewById(R.id.log_email);
        mAuth=FirebaseAuth.getInstance();
        loadingbar=new ProgressDialog(this);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                mUser = firebaseAuth.getCurrentUser();
                if (mUser != null) {
                    Log.d("User login in status..............", "User logged in!!");
                    Toast.makeText(MainActivity.this, "Verified User", Toast.LENGTH_SHORT).show();
                    sendUserToMainActivity();
                    finish();
                }
            }
        };
        mUser=mAuth.getCurrentUser();
        password = findViewById(R.id.log_password);
        login = findViewById(R.id.btn_login);
        newUser = findViewById(R.id.text_new_user);
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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingbar.setMessage("Logging In");
                loadingbar.setCancelable(false);
                loadingbar.show();
                String getEmail = email.getText().toString();
                String getPassword = password.getText().toString();
                if (getEmail.isEmpty() && getPassword.length() < 6) {
                    email.setError("Invalid Credentials");
                    password.setError("At least 6 characters Required");
                    Toast.makeText(MainActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                } else {
                    loginUser(getEmail, getPassword);
                }
            }
        });
    }

    private void sendUserToMainActivity() {
        Intent intent=new Intent(MainActivity.this,DashBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Verified User", Toast.LENGTH_SHORT).show();
                    sendUserToMainActivity();
                    loadingbar.dismiss();
                }else{
                    String error=task.getException().getMessage();
                    Toast.makeText(MainActivity.this, "Error:"+error, Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth!=null){
            mAuth.addAuthStateListener(authStateListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }

}