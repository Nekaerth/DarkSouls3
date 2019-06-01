package com.example.darksouls3;

import android.support.v4.app.Fragment;

import org.json.JSONObject;

public abstract class ListElementFragment extends Fragment {
	private JSONObject currentElement = null;

	public JSONObject getElement() {
		return currentElement;
	}

	public void setElement(JSONObject element) {
		currentElement = element;
	}

	public abstract boolean showElement(JSONObject element);

}
