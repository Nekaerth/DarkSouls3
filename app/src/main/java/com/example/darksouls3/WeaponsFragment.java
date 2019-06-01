package com.example.darksouls3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
		View view = inflater.inflate(R.layout.fragment_weapons, container, false);
		findViewsById(view);
		if (getElement() != null) {
			updateViews(getElement());
		}
		return view;
	}

	private void updateViews(JSONObject element) {
		try {
			name.setText(element.getString("name"));
			weaponType.setText("Great Sword");
		} catch (JSONException e) {
		}
	}

	private void findViewsById(View view) {
		name = view.findViewById(R.id.name);
		weaponType = view.findViewById(R.id.weaponType);
	}
}
