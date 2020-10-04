package com.example.covid_19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    Context c;
    String[] names = {"World","Afghanistan", "Akrotiri","Albania","Algeria","American Samoa","Andorra","Angola","Anguilla",
            "Antarctica", "Finland","Iraq","Syria"};


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
