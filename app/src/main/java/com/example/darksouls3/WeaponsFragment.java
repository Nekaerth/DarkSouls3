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
	private TextView type;
	private TextView weight;
	private TextView physicalValue;
	private TextView magicValue;
	private TextView fireValue;
	private TextView lightningValue;
	private TextView darkValue;

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

	@Override
	public boolean showElement(JSONObject element) {
		return false;
	}

	private void updateViews(JSONObject element) {
		try {
			name.setText(element.getString("name"));
			type.setText("Great Sword");
		} catch (JSONException e) {
		}
	}

	private void findViewsById(View view) {
		name = view.findViewById(R.id.name);
		type = view.findViewById(R.id.type);
		//weight = view.findViewById(R.id.weight);
		physicalValue = view.findViewById(R.id.physicalValue);
		magicValue = view.findViewById(R.id.magicValue);
		fireValue = view.findViewById(R.id.fireValue);
		lightningValue = view.findViewById(R.id.lightningValue);
		darkValue = view.findViewById(R.id.darkValue);
	}
}
