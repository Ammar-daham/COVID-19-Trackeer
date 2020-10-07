package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
    }
    public void MainActivity(View view) {
        int pos = 0;
        SharedPreferences getPrf = getSharedPreferences("value", Activity.MODE_PRIVATE);
        pos = getPrf.getInt("countryIndex", 0);

        MainActivity main = new MainActivity();
        main.putSharedPreference(pos);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void OhjeetActivity(View view) {
        Intent intent = new Intent(this, OhjeetActivity.class);
        startActivity(intent);
    }
}