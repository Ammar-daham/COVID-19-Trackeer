package com.example.covid_19tracker;

import java.util.ArrayList;

public class Singleton {
    private static final Singleton ourInstance= new Singleton();
    private ArrayList<String> countries2;
    public static Singleton getInstance(){
        return ourInstance;
    }
    //Adding countries
    private Singleton(){
        countries2= new ArrayList<String>();
        countries2.add("Maailma");
        countries2.add("Suomi");

    }
    //return countries
    public ArrayList<String> getCountries2(){
        return countries2;
    }
}
