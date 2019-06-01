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

public class ArmorFragment extends ListElementFragment {

	private TextView name;
	private TextView details;
	private TextView physicalValue;
	private TextView strikeValue;
	private TextView slashValue;
	private TextView thrustValue;
	private TextView magicValue;
	private TextView fireValue;
	private TextView lightningValue;
	private TextView darkValue;
	private TextView bleedValue;
	private TextView poisonValue;
	private TextView frostValue;
	private TextView curseValue;
	private TextView poiseValue;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_armor, container, false);
		findViewsById(view);
		if (getElement() != null) {
			updateViews(getElement());
		}
		return view;
	}

	private void findViewsById(View view) {
		name = view.findViewById(R.id.armorName);
		details = view.findViewById(R.id.armorDetails);
		physicalValue = view.findViewById(R.id.armorPhysicalValue);
		strikeValue = view.findViewById(R.id.armorStrikeValue);
		slashValue = view.findViewById(R.id.armorSlashValue);
		thrustValue = view.findViewById(R.id.armorThrustValue);
		magicValue = view.findViewById(R.id.armorFireValue);
		fireValue = view.findViewById(R.id.armorFireValue);
		lightningValue = view.findViewById(R.id.armorDarkValue);
		darkValue = view.findViewById(R.id.armorDarkValue);
		bleedValue = view.findViewById(R.id.armorBleedValue);
		poisonValue = view.findViewById(R.id.armorPoisonValue);
		frostValue = view.findViewById(R.id.armorFrostValue);
		curseValue = view.findViewById(R.id.armorCurseValue);
		poiseValue = view.findViewById(R.id.armorPoiseValue);
	}

	private void updateViews(JSONObject element) {
		try {
			name.setText(element.getString("name"));
			details.setText(element.getString("armor_type") + " - " + element.getString("weight") + " lbs");
			physicalValue.setText(element.getString("physical"));
			strikeValue.setText(element.getString("vs_strike"));
			slashValue.setText(element.getString("vs_slash"));
			thrustValue.setText(element.getString("vs_thrust"));
			magicValue.setText(element.getString("magic"));
			fireValue.setText(element.getString("fire"));
			lightningValue.setText(element.getString("lightning"));
			darkValue.setText(element.getString("dark"));
			bleedValue.setText(element.getString("bleed"));
			poisonValue.setText(element.getString("poison"));
			frostValue.setText(element.getString("frost"));
			curseValue.setText(element.getString("curse"));
			poiseValue.setText(element.getString("poise"));
		} catch (JSONException e) {
		}
	}

}
