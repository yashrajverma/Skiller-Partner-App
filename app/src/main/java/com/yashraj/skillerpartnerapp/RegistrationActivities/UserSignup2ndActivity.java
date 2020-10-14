package com.yashraj.skillerpartnerapp.RegistrationActivities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yashraj.skillerpartnerapp.R;

import java.util.Calendar;

public class UserSignup2ndActivity extends AppCompatActivity {
    TextView titleText;
    Button next;
    RadioButton selectedGender;
    RadioGroup radioGroup;
    DatePicker agePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup2nd);
        titleText = findViewById(R.id.register_title_text);
        next = findViewById(R.id.register_next_button);
        radioGroup = findViewById(R.id.register_gender);
        agePicker = findViewById(R.id.register_age);

    }

    public void callNextSignupScreen(View view) {
        if (!validateGender() | !validateAge()) {
            return;
        }
        String getName = getIntent().getStringExtra("name");
        String getEmail = getIntent().getStringExtra("email");
        String getPassword = getIntent().getStringExtra("password");
        String getPhone = getIntent().getStringExtra("phone");

        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());
        String getGender = selectedGender.getText().toString();
        int day = agePicker.getDayOfMonth();
        int month = agePicker.getMonth();
        int year = agePicker.getYear();
        String getAge = day + " - " + month + " - " + year;
        Intent intent = new Intent(getApplicationContext(), UserSignup3rdActivity.class);
        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View, String>(titleText, "transition_title_text");
        pairs[1] = new Pair<View, String>(next, "transition_next_button");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(UserSignup2ndActivity.this, pairs);
        intent.putExtra("name", getName);
        intent.putExtra("email", getEmail);
        intent.putExtra("password", getPassword);
        intent.putExtra("phone", getPhone);
        intent.putExtra("age", getAge);
        intent.putExtra("gender", getGender);
        startActivity(intent, options.toBundle());

    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = agePicker.getYear();
        int isAgeValid = currentYear - userAge;
        if (isAgeValid < 18) {
            Toast.makeText(this, "You're nor eligible to work", Toast.LENGTH_SHORT).show();
            return false;

        } else {
            return true;
        }
    }
}