package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    //UI Views
    private TextView titleTv;
    private BottomNavigationView navigationView;

    //fragments
    private Fragment tilastotFragment, ohjeetFragment;
    private Fragment activeFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init UI Views
        titleTv = findViewById(R.id.titleTv);
        navigationView = findViewById(R.id.navigationView);

        initFragments();


        navigationView.setOnNavigationItemSelectedListener(this);
    }

    private void initFragments() {
        //init fragments
        tilastotFragment = new TilastotFragment();
        ohjeetFragment = new OhjeetFragment();

        fragmentManager = getSupportFragmentManager();
        activeFragment = tilastotFragment;

        fragmentManager.beginTransaction()
                .add(R.id.frame, tilastotFragment, "tilastotFragment")
                .commit();
        fragmentManager.beginTransaction()
                .add(R.id.frame, ohjeetFragment, "ohjeetFragment")
                .hide(ohjeetFragment)
                .commit();
    }

    private void loadTilastotFragment(){
        titleTv.setText("Tilasto");
        fragmentManager.beginTransaction().hide(activeFragment).show(tilastotFragment).commit();
        activeFragment = tilastotFragment;
    }

    private void loadOhjeetFragment(){
        titleTv.setText("Ohjeet");
        fragmentManager.beginTransaction().hide(activeFragment).show(ohjeetFragment).commit();
        activeFragment = ohjeetFragment;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //handle bottom nav item clicks
        switch (item.getItemId()){
            case R.id.nav_tilasto:
                loadTilastotFragment();
                return true;
            case R.id.nav_ohjeet:
                loadOhjeetFragment();
                return true;
        }
        return false;
    }
}