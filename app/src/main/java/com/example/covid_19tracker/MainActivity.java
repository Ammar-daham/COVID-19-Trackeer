package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //UI Views
    TextView all, kuolematValue, atiivisetValue,toipuneetValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init UI Views
        all = findViewById(R.id.allTv);
        kuolematValue = findViewById(R.id.kuolematValue);
        atiivisetValue = findViewById(R.id.AtiivisetValue);
        toipuneetValue = findViewById(R.id.ToipuneetValue);
        Spinner list = findViewById(R.id.spinner);
        CustomAdapter adapter = new CustomAdapter(this);
        list.setAdapter(adapter);
        fetchdata();

    }
    private String allCases,recoverdCases, deathsCases, aktiiviset;
    //get request to the api data
    private void fetchdata() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://covid19.mathdro.id/api    ";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    allCases =  jsonObject.getJSONObject("confirmed").getString("value");
                    recoverdCases =  jsonObject.getJSONObject("recovered").getString("value");
                    deathsCases =  jsonObject.getJSONObject("deaths").getString("value");

                    aktiiviset = String.valueOf(
                            Integer.valueOf(allCases) -(
                                    Integer.valueOf(recoverdCases) + Integer.valueOf(deathsCases)
                                    )
                    );
                    all.setText(allCases);
                    toipuneetValue.setText(recoverdCases);
                    atiivisetValue.setText(aktiiviset);
                    kuolematValue.setText(deathsCases);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);

    }

    public void OhjeetActivity(View view) {
        Intent intent = new Intent(this, OhjeetActivity.class);
        startActivity(intent);
    }
    public void LocationActivity(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }



}