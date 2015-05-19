package com.example.pro_z.activites;

import java.io.IOException;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.pro_z.R;
import com.example.pro_z.engine.MapView;
import com.example.pro_z.loading.MapLoader;
/**
 *Questa classe istanzia {@link MapView} per la rappresentazione della mappa da visualizzare,
 * istanziando gli elementi grafici che servono per costruire l'oggetto
 * @author Davide
 *
 */
public class GameActivity extends Activity {
	private static final int MIN_TIME = 10000;
	private static final int MIN_DISTANCE = 20;

	private LocationManager manager;
	String locationProvider = LocationManager.GPS_PROVIDER;

	private Display display;
	private MapLoader loader;
	private ImageView player;
	private String mapName;
	
	private MapView map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		player = (ImageView) findViewById(R.id.player);
		loader = new MapLoader();
		display = getWindowManager().getDefaultDisplay();
		
		try {
			loader.load(mapName, display.getWidth(), display.getHeight());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map = new MapView(display, player);

		
		map.getLocationListener().addObserver(map);
		
		manager = (LocationManager) getSystemService(LOCATION_SERVICE);
	}

	@Override
	protected void onResume() {

		super.onResume();

		manager.requestLocationUpdates(locationProvider, MIN_TIME,
				MIN_DISTANCE, map.getLocationListener());
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (manager != null && manager.isProviderEnabled(locationProvider))
			manager.removeUpdates(map.getLocationListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
