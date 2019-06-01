package com.example.darksouls3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class RingsFragment extends ListElementFragment {

	private TextView name;
	private TextView type;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_rings, container, false);
		findViewsById(view);
		if (getElement() != null) {
			updateViews(getElement());
		}
		return view;
	}

	private void updateViews(JSONObject element) {
		try {
			name.setText(element.getString("name"));
			type.setText("Rank "
				+ element.getString("rank")
				+ ", "
				+ element.getString("weight")
				+ "lbs");
		} catch (JSONException e) {
		}
	}

	private void findViewsById(View view) {
		name = view.findViewById(R.id.ring_name);
		type = view.findViewById(R.id.ring_type);
	}

}
