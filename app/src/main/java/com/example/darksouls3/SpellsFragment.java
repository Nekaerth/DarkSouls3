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

public class SpellsFragment extends ListElementFragment {

	private TextView name;
	private TextView type;
	private TextView faith;
	private TextView intelligence;
	private TextView slots;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_spells, container, false);
		findViewsById(view);
		if (getElement() != null) {
			updateViews(getElement());
		}
		return view;
	}

	private void updateViews(JSONObject element) {
		try {
			name.setText(element.getString("name"));
			type.setText(element.getString("spell_type"));
			faith.setText(element.getString("faith_req"));
			intelligence.setText(element.getString("int_req"));
			slots.setText(element.getString("slots"));
		} catch (JSONException e) {
		}
	}

	private void findViewsById(View view) {
		name = view.findViewById(R.id.spell_name);
		type = view.findViewById(R.id.spell_type);
		faith = view.findViewById(R.id.spell_faith_value);
		intelligence = view.findViewById(R.id.spell_int_value);
		slots = view.findViewById(R.id.spell_slots_value);
	}
}
