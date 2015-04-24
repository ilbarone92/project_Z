package com.example.pro_z;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class GameActivity extends Activity {

	private LocationManager manager;
	private static final int MIN_TIME = 10000;
	private static final int MIN_DISTANCE = 20;
	String locationProvider = LocationManager.GPS_PROVIDER;
	private double latitude;
	private double longitude;

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
			latitude = location.getLatitude();
			longitude = location.getLongitude();
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

	}

	@Override
	protected void onResume() {
		super.onResume();
		manager = (LocationManager) getSystemService(LOCATION_SERVICE);
		manager.requestLocationUpdates(locationProvider, MIN_TIME,
				MIN_DISTANCE, listener);
		Location location = manager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null)
			updateGUI(location);

	}

	public void updateGUI(Location location) {
		// TODO metodo che controlla se è dentro al quadrato o no, se è vero
		// calcola la posizione con dx e dy rispetto ad un punto e posiziona il
		// pallino sulla mappa di conseguenza
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
