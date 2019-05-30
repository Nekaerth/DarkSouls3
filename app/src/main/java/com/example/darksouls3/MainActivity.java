package com.example.darksouls3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private APIManager APImanager;
    private JSONObject weapons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APImanager = new APIManager(this);
    }

    public void updateWeapoons(JSONObject weapons) {
        this.weapons = weapons;
        //Update GUI elements
        final EditText searchBar = findViewById(R.id.searchBar);
        if (weapons == null) {
            searchBar.setHint("Fail");
        } else {
            searchBar.setHint("Success");
        }
    }

    public void handleSearchButton(View view){
        APImanager.getWeapons();
    }
}