package com.example.darksouls3;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class APIManager {

	private final MainActivity mainActivity;
	private String url = "https://mugenmonkey.com/api/v0/";

	public APIManager(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	public void getWeapons() {
		request("ds3_weapons?per_page=1",
			new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					int count = 1;
					try {
						count = response.getInt("count");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					request("ds3_weapons?per_page=" + count,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								mainActivity.updateWeapons(response);
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								mainActivity.updateWeapons(null);
							}
						});
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					mainActivity.updateWeapons(null);
				}
			});
	}

	private void request(String urlEnd, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
		JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url + urlEnd, null, listener, errorListener);
		requestQueue.add(objectRequest);
	}
}
