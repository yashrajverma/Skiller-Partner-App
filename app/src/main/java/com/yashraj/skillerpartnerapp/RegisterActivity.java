package com.yashraj.skillerpartnerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    String cities[] = {"Choose City", "Agra", "Mathura", "Delhi", "Varanasi", "Kanpur", "Other"};
    ArrayAdapter<String> adp;
    private boolean state = false;
    private EditText name;
    private EditText email;
    private EditText password;
    private Button registerButton;
    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sp = findViewById(R.id.spinner);
        name = findViewById(R.id.reg_name);
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);
        registerButton = findViewById(R.id.btn_login);
        adp = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_list_item_1, cities);
        sp.setAdapter(adp);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, DashBoardActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.state = false;
    }

    @Override
    public void onBackPressed() {
        if (state) {
            super.onBackPressed();
        } else {
            this.state = false;
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
    }
}