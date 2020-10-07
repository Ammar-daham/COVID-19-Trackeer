package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    //UI Views
    TextView all, kuolematValue, aktiivisetValue,toipuneetValue;
    public int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init UI Views
        all = findViewById(R.id.allTv);
        kuolematValue = findViewById(R.id.kuolematValue);
        aktiivisetValue = findViewById(R.id.AtiivisetValue);
        toipuneetValue = findViewById(R.id.ToipuneetValue);
        Spinner list = findViewById(R.id.spinner);

        list.setAdapter(new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,
                Singleton.getInstance().getCountries2()
        ));



        //using the setonItemSelectedListener to activate the choosen country from the list
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //index 0
                if(position == 0) {
                    pos = position;
                    fetchdata();
                }
                //other index
                else {
                    pos = position;
                    fetchdataFinland();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //objects
    private String allCases, recoverdCases, deathsCases, aktiiviset;
    String url = "https://covid19.mathdro.id/api/";

    //this methode to get the world statistics from the JSON file through api
    private void fetchdata() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    allCases = jsonObject.getJSONObject("confirmed").getString("value");
                    recoverdCases = jsonObject.getJSONObject("recovered").getString("value");
                    deathsCases = jsonObject.getJSONObject("deaths").getString("value");

                    aktiiviset = String.valueOf(
                            Integer.valueOf(allCases) - (
                                    Integer.valueOf(recoverdCases) + Integer.valueOf(deathsCases)
                            )
                    );
                    all.setText(allCases);
                    toipuneetValue.setText(recoverdCases);
                    aktiivisetValue.setText(aktiiviset);
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

    //this methode to get the Finland statistics from the JSON file through api
    private void fetchdataFinland() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String urlFinland = url + "countries/finland";
        StringRequest request = new StringRequest(Request.Method.GET, urlFinland, new Response.Listener<String>() {
            @Override

            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    allCases = jsonObject.getJSONObject("confirmed").getString("value");
                    recoverdCases = jsonObject.getJSONObject("recovered").getString("value");
                    deathsCases = jsonObject.getJSONObject("deaths").getString("value");

                    aktiiviset = String.valueOf(
                            Integer.valueOf(allCases) - (
                                    Integer.valueOf(recoverdCases) + Integer.valueOf(deathsCases)
                            )
                    );
                    all.setText(allCases);
                    toipuneetValue.setText(recoverdCases);
                    aktiivisetValue.setText(aktiiviset);
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


    public void putSharedPreference() {
        SharedPreferences getPref = getSharedPreferences("value", Activity.MODE_PRIVATE);
        SharedPreferences.Editor putPref = getPref.edit();
        putPref.putInt("countryIndex", pos);
        putPref.commit();

    }



    //these methods to change to other activities
    public void OhjeetActivity(View view) {
        putSharedPreference();
        Log.d("save value", "save");
        Intent intent = new Intent(this, OhjeetActivity.class);
        startActivity(intent);
    }
    public void LocationActivity(View view) {
        putSharedPreference();
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

}