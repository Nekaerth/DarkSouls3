package com.example.darksouls3;

import android.support.v4.app.Fragment;

import org.json.JSONObject;

public abstract class ListElementFragment extends Fragment {

	public abstract boolean showElement(JSONObject element);

}
