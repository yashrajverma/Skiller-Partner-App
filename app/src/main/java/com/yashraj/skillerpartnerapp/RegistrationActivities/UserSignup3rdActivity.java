package com.yashraj.skillerpartnerapp.RegistrationActivities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yashraj.skillerpartnerapp.R;

public class UserSignup3rdActivity extends AppCompatActivity {
    TextView titleText;
    Button next;
    RadioButton selectedSkill;
    RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup3rd);
        titleText = findViewById(R.id.register_title_text);
        next = findViewById(R.id.register_next_button);
        radioGroup = findViewById(R.id.register_skill);


    }

    public void callNextSignupScreen(View view) {
        if (!validateSkill()) {
            return;
        }
        selectedSkill = findViewById(radioGroup.getCheckedRadioButtonId());
        String skill = selectedSkill.getText().toString();
        String getName = getIntent().getStringExtra("name");
        String getEmail = getIntent().getStringExtra("email");
        String getPassword = getIntent().getStringExtra("password");
        String getPhone = getIntent().getStringExtra("phone");
        String getAge = getIntent().getStringExtra("age");
        String getGender = getIntent().getStringExtra("gender");
        String getState = getIntent().getStringExtra("state");
        String getCity = getIntent().getStringExtra("city");
        Integer getCharges = getIntent().getIntExtra("charges", 100);

        Intent intent = new Intent(getApplicationContext(), PhoneAuthenticationActivity.class);
        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View, String>(titleText, "transition_title_text");
        pairs[1] = new Pair<View, String>(next, "transition_next_button");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(UserSignup3rdActivity.this, pairs);
        intent.putExtra("name", getName);
        intent.putExtra("email", getEmail);
        intent.putExtra("password", getPassword);
        intent.putExtra("phone", getPhone);
        intent.putExtra("age", getAge);
        intent.putExtra("gender", getGender);
        intent.putExtra("occupation", skill);
        intent.putExtra("state", getState);
        intent.putExtra("city", getCity);
        intent.putExtra("charges", getCharges);
        startActivity(intent, options.toBundle());

    }

    private boolean validateSkill() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Nothing has been Selected", Toast.LENGTH_SHORT).show();
            return false;
        } else {

            return true;
        }
    }

}