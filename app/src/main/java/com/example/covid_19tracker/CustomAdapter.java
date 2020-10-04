package com.example.covid_19tracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context c;
    String[] names = new String[300];

    private JSONArray allCountries;
    private ArrayList<String> country = new ArrayList<>();


    String urlCountries = "https://covid19.mathdro.id/api/countries";
    StringRequest requestCountries = new StringRequest(Request.Method.GET, urlCountries, new Response.Listener<String>() {
        @Override

        public void onResponse(String response) {
            try {

                JSONObject jsonObject = new JSONObject(response.toString());
                allCountries = jsonObject.getJSONArray("countries");
                names[0] = "World";
                for(int i=1; i < allCountries.length(); i++){
                    names[i]=allCountries.getJSONObject(i).getString("name");
                    Log.d("json", names[i]);
                }

                Log.d("before all countries", "yes");

                Log.d( "onResponse", String.valueOf(allCountries));
            } catch (JSONException e) {
                Log.d("error", "there is ");
                e.printStackTrace();
            }
        }
    },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error)
                {
                    Toast.makeText(c,
                            error.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            });




    public CustomAdapter(Context c) {
        this.c = c;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.spinner_item,null);
        }
        //get view
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);

        //set data
        tvName.setText(names[position]);

        return  convertView;
    }
}
