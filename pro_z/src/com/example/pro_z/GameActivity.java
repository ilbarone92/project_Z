package com.example.pro_z;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends Activity {
	private static final int MIN_TIME = 10000;
	private static final int MIN_DISTANCE = 20;
	private static final int CIRC_EQUATORE = 40075040;
	private static final int CIRC_MERIDIANO = 40009000;

	private LocationManager manager;
	String locationProvider = LocationManager.GPS_PROVIDER;
	private Display display;
	private int screenWidth;
	private int screenHeight;
	private View viewY;
	private View viewX;
	private ImageView pallino;

	LocationListener listener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onLocationChanged(Location location) {
			updateGUI(location);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		pallino = (ImageView) findViewById(R.id.pallino);
		viewX = (View) findViewById(R.id.ViewX);
		viewY = (View) findViewById(R.id.ViewY);

		manager = (LocationManager) getSystemService(LOCATION_SERVICE);
		display = getWindowManager().getDefaultDisplay();

		screenWidth = display.getWidth();
		screenHeight = display.getHeight();

		viewX.setLayoutParams(new LayoutParams((screenWidth / 2) - (pallino.getWidth() / 2), viewX.getHeight()));
		viewY.setLayoutParams(new LayoutParams(viewY.getWidth(), (screenHeight / 2) - (pallino.getHeight() / 2)));
	}

	@Override
	protected void onResume() {
		super.onResume();
		manager.requestLocationUpdates(locationProvider, MIN_TIME, MIN_DISTANCE, listener);
		Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null)
			updateGUI(location);

	}

	public void updateGUI(Location location) {
		double latitude;
		double longitude;

		latitude = location.getLatitude();
		longitude = location.getLongitude();

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (manager != null && manager.isProviderEnabled(locationProvider))
			manager.removeUpdates(listener);
	}

	@Override
	public void onBackPressed() {
		Toast.makeText(this, "Adios!", Toast.LENGTH_SHORT).show();
		super.onBackPressed();
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
