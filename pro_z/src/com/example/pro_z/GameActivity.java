package com.example.pro_z;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends Activity {
	private static final int MIN_TIME = 10000;
	private static final int MIN_DISTANCE = 20;

	private LocationManager manager;
	String locationProvider = LocationManager.GPS_PROVIDER;

	private Display display;
	private View viewY;
	private View viewX;
	private ImageView pallino;

	private MyLocationListener locationListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		pallino = (ImageView) findViewById(R.id.pallino);
		viewX = (View) findViewById(R.id.ViewX);
		viewY = (View) findViewById(R.id.ViewY);
		display = getWindowManager().getDefaultDisplay();

		locationListener = new MyLocationListener(display, pallino, viewX,
				viewY);

		manager = (LocationManager) getSystemService(LOCATION_SERVICE);
	}

	@Override
	protected void onResume() {

		super.onResume();

		manager.requestLocationUpdates(locationProvider, MIN_TIME,
				MIN_DISTANCE, locationListener);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (manager != null && manager.isProviderEnabled(locationProvider))
			manager.removeUpdates(locationListener);
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
