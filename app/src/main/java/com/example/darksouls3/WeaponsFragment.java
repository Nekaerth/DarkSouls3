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
	private TextView leftHeader;
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
		name = view.findViewById(R.id.weaponName);
		details = view.findViewById(R.id.weaponDetails);
		leftHeader = view.findViewById(R.id.weaponLeftHeader);
		physicalValue = view.findViewById(R.id.weaponPhysicalValue);
		magicValue = view.findViewById(R.id.weaponMagicValue);
		fireValue = view.findViewById(R.id.weaponFireValue);
		lightningValue = view.findViewById(R.id.weaponLightningValue);
		darkValue = view.findViewById(R.id.weaponDarkValue);
		strengthValue = view.findViewById(R.id.weaponStrengthValue);
		dexterityValue = view.findViewById(R.id.weaponDexterityValue);
		intelligenceValue = view.findViewById(R.id.weaponIntelligenceValue);
		faithValue = view.findViewById(R.id.weaponFaithValue);
	}

	private void updateViews(JSONObject element) {
		try {
			name.setText(element.getString("name"));
			details.setText(element.getString("weapon_type") + " - " + element.getString("weight") + " lbs");

			boolean isShield = element.getString("weapon_type").equals("Shield");

			if (isShield) {
				leftHeader.setText("Absorption");
				physicalValue.setText(element.getString("physical_def"));
				magicValue.setText(element.getString("magic_def"));
				fireValue.setText(element.getString("fire_def"));
				lightningValue.setText(element.getString("lightning_def"));
				darkValue.setText(element.getString("dark_def"));
			} else {
				leftHeader.setText("Attach Damage");
				String damagesString = element.getString("base_damage");
				String[] damagesList = damagesString.substring(1).split(", ", 6);
				physicalValue.setText(damagesList[0].split("\\.")[0]);
				magicValue.setText(damagesList[1].split("\\.")[0]);
				fireValue.setText(damagesList[2].split("\\.")[0]);
				lightningValue.setText(damagesList[3].split("\\.")[0]);
				darkValue.setText(damagesList[4].split("\\.")[0]);
			}

			strengthValue.setText(element.getString("strength_req"));
			dexterityValue.setText(element.getString("dex_req"));
			intelligenceValue.setText(element.getString("intelligence_req"));
			faithValue.setText(element.getString("faith_req"));
		} catch (JSONException e) {
		}
	}

}
