package com.example.darksouls3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class WeaponsFragment extends ListElementFragment {

	private TextView name;
	private TextView weaponType;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.e("onCreateView", "called");
		View view = inflater.inflate(R.layout.fragment_weapons, container, false);
		name = view.findViewById(R.id.name);
		weaponType = view.findViewById(R.id.weaponType);
		if (getElement() != null) {
			showElement(getElement());
		}
		return view;
	}

	private void showElement(JSONObject element) {
		Log.e("showElement", "called");
		try {
			name.setText(element.getString("name"));
			weaponType.setText("Great Sword");
		} catch (JSONException e) {
			return;
		}
		return;
	}
}
