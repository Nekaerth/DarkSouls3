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

	private void request(String urlEnd, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
		RequestQueue requestQueue = Volley.newRequestQueue(mainActivity);
		JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url + urlEnd, null, listener, errorListener);
		requestQueue.add(objectRequest);
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
								mainActivity.setWeapons(response);
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								mainActivity.setWeapons(null);
							}
						});
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					mainActivity.setWeapons(null);
				}
			});
	}

	public void getSpells() {
		request("ds3_spells?per_page=1",
			new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					int count = 1;
					try {
						count = response.getInt("count");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					request("ds3_spells?per_page=" + count,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								mainActivity.setSpells(response);
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								mainActivity.setSpells(null);
							}
						});
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					mainActivity.setSpells(null);
				}
			});
	}

	public void getRings() {
		request("ds3_rings?per_page=1",
			new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					int count = 1;
					try {
						count = response.getInt("count");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					request("ds3_rings?per_page=" + count,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								mainActivity.setRings(response);
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								mainActivity.setRings(null);
							}
						});
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					mainActivity.setRings(null);
				}
			});
	}

	public void getArmors() {
		request("ds3_armors?per_page=1",
			new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					int count = 1;
					try {
						count = response.getInt("count");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					request("ds3_armors?per_page=" + count,
						new Response.Listener<JSONObject>() {
							@Override
							public void onResponse(JSONObject response) {
								mainActivity.setArmors(response);
							}
						},
						new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								mainActivity.setArmors(null);
							}
						});
				}
			},
			new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					mainActivity.setArmors(null);
				}
			});
	}
}
