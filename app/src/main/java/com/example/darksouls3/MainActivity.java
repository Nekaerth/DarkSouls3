package com.example.darksouls3;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handleBottomNavigationView();

        //Testing af API
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://mugenmonkey.com/api/v0/ds3_weapons";
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response){
                        Log.e("REST Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.e("REST Response", error.toString());
                    }
                }
        );
        requestQueue.add(objectRequest);
    }

    private void handleBottomNavigationView() {
        BottomNavigationView btmNavView = findViewById(R.id.bottom_navigation);
        btmNavView.setOnNavigationItemSelectedListener(btmNavViewListener);
        getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container,
                new HomeFragment()
        ).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener btmNavViewListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_weapons:
                            selectedFragment = new WeaponsFragment();
                            break;
                        case R.id.nav_spells:
                            selectedFragment = new SpellsFragment();
                            break;
                        case R.id.nav_rings:
                            selectedFragment = new RingsFragment();
                            break;
                        case R.id.nav_armor:
                            selectedFragment = new ArmorFragment();
                            break;
                    }

                    if (selectedFragment == null) {
                        return false;
                    }

                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.fragment_container,
                            selectedFragment
                    ).commit();
                    return true;
                }
            };
}