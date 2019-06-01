package com.example.darksouls3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;

public class WeaponsFragment extends ListElementFragment {

	private TextView name;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_weapons, container, false);
		name = view.findViewById(R.id.name);
		return view;
	}

	@Override
	public boolean showElement(JSONObject element) {
		name.setText("Black Knight Sword");
		return false;
	}
}
