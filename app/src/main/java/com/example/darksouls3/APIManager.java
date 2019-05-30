package com.example.darksouls3;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class APIManager {

    private final MainActivity mainActivity;
    private String url = "https://mugenmonkey.com/api/v0/";

    public APIManager(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void getWeapons() {
        RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url + "ds3_weapons",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response){
                        mainActivity.updateWeapoons(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        mainActivity.updateWeapoons(null);
                    }
                }
        );
        requestQueue.add(objectRequest);
    }
}
