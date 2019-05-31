package com.example.darksouls3;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

	private APIManager APImanager;
	private JSONObject weapons;
	private JSONObject spells;
	private JSONObject rings;
	private JSONObject armors;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		APImanager = new APIManager(this);
		handleBottomNavigationView();
	}

	private void tryUpdateWeaponList() {
		if (weapons == null) {
			APImanager.getWeapons();
		} else {
			updateWeaponList();
		}
	}

	public void setWeapons(JSONObject weapons) {
		if (weapons != null) {
			this.weapons = weapons;
			updateWeaponList();
		} else {
			//Clear list?
		}
	}

	private void updateWeaponList() {
		//Make list
	}

	private void tryUpdateSpellList() {
		if (spells == null) {
			APImanager.getSpells();
		} else {
			updateSpellList();
		}
	}

	public void setSpells(JSONObject spells) {
		if (spells != null) {
			this.spells = spells;
			updateSpellList();
		} else {
			//Clear list?
		}
	}

	private void updateSpellList() {
		//Make list
	}

	private void tryUpdateRingList() {
		if (rings == null) {
			APImanager.getRings();
		} else {
			updateRingList();
		}
	}

	public void setRings(JSONObject rings) {
		if (rings != null) {
			this.rings = rings;
			updateRingList();
		} else {
			//Clear list?
		}
	}

	private void updateRingList() {
		//Make list
	}

	private void tryUpdateArmorList() {
		if (armors == null) {
			APImanager.getArmors();
		} else {
			updateArmorList();
		}
	}

	public void setArmors(JSONObject armors) {
		if (armors != null) {
			this.armors = armors;
			updateArmorList();
		} else {
			//Clear list?
		}
	}

	private void updateArmorList() {
		//Make list
	}

	public void handleSearchButton(View view) {
		APImanager.getWeapons();
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
						break;
					case R.id.nav_spells:
						selectedFragment = new SpellsFragment();
						break;
					case R.id.nav_rings:
						selectedFragment = new RingsFragment();
						break;
					case R.id.nav_armor:
						selectedFragment = new ArmorFragment();
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
}
