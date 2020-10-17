package com.yashraj.skillerpartnerapp.RegistrationActivities;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.yashraj.skillerpartnerapp.R;

import java.util.List;
import java.util.Locale;

public class UserSignup2ndActivity extends AppCompatActivity implements LocationListener {
    TextView titleText;
    Button next, showLocation;
    EditText state, city;
    RadioButton selectedGender;
    RadioGroup radioGroup;
    DatePicker agePicker;
    LocationManager locationManager;
    private double latitude;
    private double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_signup2nd);
        initView();

        showLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(UserSignup2ndActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(UserSignup2ndActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {
                    detectCurrentLocation();
                }
            }
        });


    }

    private void initView() {
        titleText = findViewById(R.id.register_title_text);
        next = findViewById(R.id.register_next_button);
        state = findViewById(R.id.register_state);
        city = findViewById(R.id.register_city);
        showLocation = findViewById(R.id.register_show_location);
        radioGroup = findViewById(R.id.register_gender);
//        agePicker = findViewById(R.id.register_age);

    }

    private void detectCurrentLocation() {
        Toast.makeText(this, "please wait", Toast.LENGTH_SHORT).show();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }

    public void callNextSignupScreen(View view) {
        if (!validateGender() | !validateState() | !validateCity()) {
            return;
        }
        String getName = getIntent().getStringExtra("name");
        String getEmail = getIntent().getStringExtra("email");
        String getPassword = getIntent().getStringExtra("password");
        String getPhone = getIntent().getStringExtra("phone");

        selectedGender = findViewById(radioGroup.getCheckedRadioButtonId());
        String getGender = selectedGender.getText().toString();
        String getState = state.getText().toString().trim();
        String getCity = city.getText().toString().trim();
        Intent intent = new Intent(getApplicationContext(), UserSignup3rdActivity.class);
        Pair[] pairs = new Pair[2];
        pairs[0] = new Pair<View, String>(titleText, "transition_title_text");
        pairs[1] = new Pair<View, String>(next, "transition_next_button");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(UserSignup2ndActivity.this, pairs);
        intent.putExtra("name", getName);
        intent.putExtra("email", getEmail);
        intent.putExtra("password", getPassword);
        intent.putExtra("phone", getPhone);
        intent.putExtra("gender", getGender);
        intent.putExtra("state", getState);
        intent.putExtra("city", getCity);
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

    private boolean validateState() {
        String value = state.getText().toString().trim();
        if (value.isEmpty()) {
            state.setError("Field can't be empty");
            return false;
        } else if (!(value.contains("Uttar Pradesh"))) {
            Toast.makeText(this, "Service available in Uttar Pradesh only", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            state.setError(null);
            return true;

        }

    }

    private boolean validateCity() {
        String value = city.getText().toString().trim();
        if (value.isEmpty()) {
            city.setError("Field can't be empty");
            return false;
        } else {
            city.setError(null);
            return true;
        }
    }


    // Location Methods
    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        findAddress();

    }

    private void findAddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
//            String countryName = addresses.get(0).getCountryName();
            String stateName = addresses.get(0).getAdminArea();
            String cityName = addresses.get(0).getLocality();
//            String fullAddress = addresses.get(0).getAddressLine(0);
            state.setText(stateName);
            city.setText(cityName);
//            address3.setText(fullAddress);


        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Please turn on location", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                detectCurrentLocation();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}