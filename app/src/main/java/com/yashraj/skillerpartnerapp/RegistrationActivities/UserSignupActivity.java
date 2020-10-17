package com.yashraj.skillerpartnerapp.RegistrationActivities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.yashraj.skillerpartnerapp.R;

public class UserSignupActivity extends AppCompatActivity {
    TextView titleText;
    Button next;
    TextInputLayout name, email, password, phoneNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup);
        //Hooks for Animation view
        titleText = findViewById(R.id.register_title_text);
        next = findViewById(R.id.register_next_button);
        //Hooks for getting data
        name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        phoneNo = findViewById(R.id.register_phone);

    }

    public void callNextSignupScreen(View view) {
        if (!validateName() | !validateEmail() | !validatePassword() | !validatePhone()) {
            return;
        }
        String getName = name.getEditText().getText().toString();
        String getEmail = email.getEditText().getText().toString();
        String getPassword = password.getEditText().getText().toString();
        String Phone = phoneNo.getEditText().getText().toString();
        String getPhone = "+91" + Phone;

        Intent intent = new Intent(getApplicationContext(), UserSignup2ndActivity.class);
        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View, String>(titleText, "transition_title_text");
        pairs[1] = new Pair<View, String>(next, "transition_next_button");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(UserSignupActivity.this, pairs);
        intent.putExtra("name", getName);
        intent.putExtra("email", getEmail);
        intent.putExtra("password", getPassword);
        intent.putExtra("phone", getPhone);

        startActivity(intent, options.toBundle());

    }

    private boolean validateName() {
        String value = name.getEditText().getText().toString().trim();
        if (value.isEmpty()) {
            name.setError("Field can't be Empty");
            return false;
        } else if (value.length() > 20) {
            name.setError("Username too large");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        String value = email.getEditText().getText().toString();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (value.isEmpty()) {
            email.setError("Field can't be empty");
            return false;
        } else if (!value.matches(checkEmail)) {
            email.setError("Invalid Email");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String value = password.getEditText().getText().toString().trim();
        if (value.length() < 6) {
            password.setError("Password too short");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String value = phoneNo.getEditText().getText().toString();
        if (value.length() < 10) {
            phoneNo.setError("Invalid Phone Number");
            return false;
        } else {
            phoneNo.setError(null);
            return true;
        }

    }
}