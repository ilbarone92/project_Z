package com.example.pro_z.activites;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.pro_z.R;
import com.example.pro_z.engine.MapModel;
import com.example.pro_z.engine.MapView;
import com.example.pro_z.engine.MyLocationListener;
import com.example.pro_z.loading.MapLoader;
import com.example.pro_z.utils.Maps;

/**
 * Questa classe istanzia {@link MapView} per la rappresentazione della mappa da visualizzare,
 * istanziando gli elementi grafici che servono per costruire l'oggetto
 * 
 * @author Davide
 * 
 */
public class GameActivity extends Activity {
	private static final int MIN_TIME = 10000;
	private static final int MIN_DISTANCE = 20;

	private LocationManager manager;
	String locationProvider = LocationManager.GPS_PROVIDER;
	private MyLocationListener listener = MyLocationListener.getMyLocationListener();

	private Display display;
	private MapLoader loader;
	private ImageView player;
	private MapModel model;

	// DEBUG
	private String mapNome = "";

	private MapView map;

	private Intent intent;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.LayoutGame);

		player = (ImageView) findViewById(R.id.player);
		loader = new MapLoader(this);
		display = getWindowManager().getDefaultDisplay();
		intent = getIntent();
		try {
			model = loader.load(intent.getStringExtra("mapName"), display.getWidth(),
					display.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
		}
		map = new MapView(display, player);

		layout.setBackgroundDrawable(getResources().getDrawable(
				Maps.get().getMaps().get(model.getMapKey())));

		mapNome = intent.getStringExtra("mapName");

		listener.addObserver(map);

		manager = (LocationManager) getSystemService(LOCATION_SERVICE);
	}

	@Override
	protected void onResume() {

		super.onResume();

		manager.requestLocationUpdates(locationProvider, MIN_TIME, MIN_DISTANCE, listener);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (manager != null && manager.isProviderEnabled(locationProvider))
			manager.removeUpdates(listener);
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

	// DEBUG
	public String getMapNome() {
		return mapNome;
	}
}
