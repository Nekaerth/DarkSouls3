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
	private TextView details;
	private TextView physicalValue;
	private TextView magicValue;
	private TextView fireValue;
	private TextView lightningValue;
	private TextView darkValue;
	private TextView strengthValue;
	private TextView dexterityValue;
	private TextView intelligenceValue;
	private TextView faithValue;

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

	private void findViewsById(View view) {
		name = view.findViewById(R.id.armorName);
		details = view.findViewById(R.id.armorDetails);
		physicalValue = view.findViewById(R.id.armorPhysicalValue);
		magicValue = view.findViewById(R.id.armorStrikeValue);
		fireValue = view.findViewById(R.id.armorSlashValue);
		lightningValue = view.findViewById(R.id.armorThrustValue);
		darkValue = view.findViewById(R.id.armorFireValue);
		strengthValue = view.findViewById(R.id.armorBleedValue);
		dexterityValue = view.findViewById(R.id.armorPoisonValue);
		intelligenceValue = view.findViewById(R.id.armorFrostValue);
		faithValue = view.findViewById(R.id.armorPoiseValue);
	}

	private void updateViews(JSONObject element) {
		try {
			name.setText(element.getString("name"));
			details.setText(element.getString("weapon_type") + " - " + element.getString("weight") + " lbs");

			String damagesString = element.getString("base_damage");
			String[] damagesList = damagesString.substring(1).split(", ", 6);
			physicalValue.setText(damagesList[0].split("\\.")[0]);
			magicValue.setText(damagesList[1].split("\\.")[0]);
			fireValue.setText(damagesList[2].split("\\.")[0]);
			lightningValue.setText(damagesList[3].split("\\.")[0]);
			darkValue.setText(damagesList[4].split("\\.")[0]);

			strengthValue.setText(element.getString("strength_req"));
			dexterityValue.setText(element.getString("dex_req"));
			intelligenceValue.setText(element.getString("intelligence_req"));
			faithValue.setText(element.getString("faith_req"));
		} catch (JSONException e) {
		}
	}

}
