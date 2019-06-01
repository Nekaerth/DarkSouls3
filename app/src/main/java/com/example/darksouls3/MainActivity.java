package com.example.darksouls3;

import android.support.v4.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
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
	private ArrayList<JSONObject> weaponJSONList = new ArrayList<JSONObject>();
	private ArrayList<JSONObject> spellJSONList = new ArrayList<JSONObject>();
	private ArrayList<JSONObject> ringJSONList = new ArrayList<JSONObject>();
	private ArrayList<JSONObject> armorJSONList = new ArrayList<JSONObject>();
	private Fragment selectedFragment = null;
	private int currentTabId = R.id.nav_weapons;

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
		listView.setOnItemClickListener(createListener());
		listManager = new ListManager(listView, this);
		tryDisplayWeaponList();
	}

	private AdapterView.OnItemClickListener createListener() {
		return new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				JSONObject currentItem;

				switch (currentTabId) {
					case R.id.nav_weapons:
						currentItem = weaponJSONList.get(position);
						break;
					case R.id.nav_spells:
						currentItem = spellJSONList.get(position);
						break;
					case R.id.nav_rings:
						currentItem = ringJSONList.get(position);
						break;
					case R.id.nav_armor:
						currentItem = armorJSONList.get(position);
						break;
				}

				if (selectedFragment == null) {
					return;
				}

				getSupportFragmentManager().beginTransaction().replace(
					R.id.fragment_container,
					selectedFragment
				).commit();
			}
		};
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
		weaponStringList = getStringList(weapons, weaponJSONList, "ds3_weapons");
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
		spellStringList = getStringList(spells, spellJSONList, "ds3_spells");
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
		ringStringList = getStringList(rings, ringJSONList, "ds3_rings");
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
		armorStringList = getStringList(armors, armorJSONList, "ds3_armors");
	}

	private void handleBottomNavigationView() {
		BottomNavigationView btmNavView = findViewById(R.id.bottom_navigation);
		btmNavView.setOnNavigationItemSelectedListener(btmNavViewListener);
		selectedFragment = new WeaponsFragment();
	}

	private BottomNavigationView.OnNavigationItemSelectedListener btmNavViewListener =
		new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
				if (currentTabId != menuItem.getItemId()) {
					listManager.clearList();
				}

				switch (menuItem.getItemId()) {
					case R.id.nav_weapons:
						selectedFragment = new WeaponsFragment();
						tryDisplayWeaponList();
						currentTabId = R.id.nav_weapons;
						break;
					case R.id.nav_spells:
						selectedFragment = new SpellsFragment();
						tryDisplaySpellList();
						currentTabId = R.id.nav_spells;
						break;
					case R.id.nav_rings:
						selectedFragment = new RingsFragment();
						tryDisplayRingList();
						currentTabId = R.id.nav_rings;
						break;
					case R.id.nav_armor:
						selectedFragment = new ArmorFragment();
						tryDisplayArmorList();
						currentTabId = R.id.nav_armor;
						break;
				}
				return true;
			}
		};

	private ArrayList<String> getStringList(JSONObject sourceJSON, ArrayList<JSONObject> dataList, String type) {
		ArrayList<String> stringList = new ArrayList<String>();
		int c = 0;
		JSONObject w;
		JSONObject e;
		try {
			c = sourceJSON.getInt("count");
			w = sourceJSON.getJSONObject(type);
		} catch (JSONException ex) {
			return null;
		}
		for (int i = 1; i < c + 1; i++) {
			String name = "Placeholder " + i;
			try {
				e = w.getJSONObject("" + i);
				name = e.getString("name");
			} catch (JSONException ex) {
				continue;
			}
			dataList.add(e);
			stringList.add(name);
		}
		return stringList;
	}
}
