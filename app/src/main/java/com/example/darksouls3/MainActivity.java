package com.example.darksouls3;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	private APIManager APImanager;
	private ListManager listManager;
	private JSONObject weapons;
	private JSONObject spells;
	private JSONObject rings;
	private JSONObject armors;
	private ArrayList<String> weaponStringList = new ArrayList<String>();
	private ArrayList<String> spellStringList = new ArrayList<String>();
	private ArrayList<String> ringStringList = new ArrayList<String>();
	private ArrayList<String> armorStringList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		APImanager = new APIManager(this);
		handleBottomNavigationView();
		handleListView();
	}

	private void handleListView() {
		ListView listView = findViewById(R.id.list_view);
		listManager = new ListManager(listView, this);
		tryDisplayWeaponList();
	}

	private void tryDisplayWeaponList() {
		if (weapons == null) {
			APImanager.getWeapons();
		} else {
			listManager.updateList(weaponStringList);
		}
	}

	public void setWeapons(JSONObject weapons) {
		if (weapons != null) {
			this.weapons = weapons;
			updateWeaponNames();
			listManager.updateList(weaponStringList);
		} else {
			//Clear list?
		}
	}

	private void updateWeaponNames() {
		weaponStringList = getStringList(weapons, "ds3_weapons");
	}

	private void tryDisplaySpellList() {
		if (spells == null) {
			APImanager.getSpells();
		} else {
			listManager.updateList(spellStringList);
		}
	}

	public void setSpells(JSONObject spells) {
		if (spells != null) {
			this.spells = spells;
			updateSpellNames();
			listManager.updateList(spellStringList);
		} else {
			//Clear list?
		}
	}

	private void updateSpellNames() {
		spellStringList = getStringList(spells,"ds3_spells");
	}

	private void tryDisplayRingList() {
		if (rings == null) {
			APImanager.getRings();
		} else {
			listManager.updateList(ringStringList);
		}
	}

	public void setRings(JSONObject rings) {
		if (rings != null) {
			this.rings = rings;
			updateRingNames();
			listManager.updateList(ringStringList);
		} else {
			//Clear list?
		}
	}

	private void updateRingNames() {
		ringStringList = getStringList(rings,"ds3_rings");
	}

	private void tryDisplayArmorList() {
		if (armors == null) {
			APImanager.getArmors();
		} else {
			listManager.updateList(armorStringList);
		}
	}

	public void setArmors(JSONObject armors) {
		if (armors != null) {
			this.armors = armors;
			updateArmorNames();
			listManager.updateList(armorStringList);
		} else {
			//Clear list?
		}
	}

	private void updateArmorNames() {
		armorStringList = getStringList(armors,"ds3_armors");
	}

	public void handleSearchButton(View view) {
		tryDisplayWeaponList();
	}

	private void handleBottomNavigationView() {
		BottomNavigationView btmNavView = findViewById(R.id.bottom_navigation);
		btmNavView.setOnNavigationItemSelectedListener(btmNavViewListener);
		getSupportFragmentManager().beginTransaction().replace(
			R.id.fragment_container,
			new HomeFragment()
		).commit();
	}

	private BottomNavigationView.OnNavigationItemSelectedListener btmNavViewListener =
		new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
				Fragment selectedFragment = null;

				switch (menuItem.getItemId()) {
					case R.id.nav_home:
						selectedFragment = new HomeFragment();
						break;
					case R.id.nav_weapons:
						selectedFragment = new WeaponsFragment();
						tryDisplayWeaponList();
						break;
					case R.id.nav_spells:
						selectedFragment = new SpellsFragment();
						tryDisplaySpellList();
						break;
					case R.id.nav_rings:
						selectedFragment = new RingsFragment();
						tryDisplayRingList();
						break;
					case R.id.nav_armor:
						selectedFragment = new ArmorFragment();
						tryDisplayArmorList();
						break;
				}

				if (selectedFragment == null) {
					return false;
				}

				getSupportFragmentManager().beginTransaction().replace(
					R.id.fragment_container,
					selectedFragment
				).commit();
				return true;
			}
		};

	private ArrayList<String> getStringList(JSONObject object, String type) {
		ArrayList<String> stringList = new ArrayList<String>();
		int c = 0;
		JSONObject w;
		try {
			c = object.getInt("count");
			w = object.getJSONObject(type);
		} catch (JSONException e) {
			return null;
		}
		for (int i = 1; i < c + 1; i++) {
			String name = "Placeholder " + i;
			try {
				name = w.getJSONObject("" + i).getString("name");
			} catch (JSONException e) {
				continue;
			}
			stringList.add(name);
		}
		return stringList;
	}
}
