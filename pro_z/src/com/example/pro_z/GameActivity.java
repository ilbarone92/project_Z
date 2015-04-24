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
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
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
	private boolean started = false;
	private double startLatitude;
	private double startLongitude;
	private int xMax;
	private float yMax;
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
			if (!started) {
				startLatitude = location.getLatitude();
				startLongitude = location.getLongitude();
				started = true;
			}
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

		
		display = getWindowManager().getDefaultDisplay();

		screenWidth = display.getWidth();
		screenHeight = display.getHeight();

		viewY.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, (screenHeight / 2) - (pallino.getHeight() / 2)));
		viewX.setLayoutParams(new LayoutParams((screenWidth / 2) - (pallino.getWidth() / 2), LayoutParams.MATCH_PARENT));
		

		xMax = 20;
		yMax = xMax * (screenHeight / screenWidth);
		
//		manager = (LocationManager) getSystemService(LOCATION_SERVICE);
	}

	@Override
	protected void onResume() {
		super.onResume();
//		manager.requestLocationUpdates(locationProvider, MIN_TIME, MIN_DISTANCE, listener);

	}

	public void updateGUI(Location location) {
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		
		//calcolo di x e y in termini di metri
		double x = (longitude - startLongitude)*CIRC_EQUATORE/360;
		double y = (latitude - startLatitude)*CIRC_MERIDIANO/180;
		
		//calcolo della x e y in termini di pixel
		int x_pixel=(int) Math.round(((((xMax/2) + x)*screenWidth/xMax)-(pallino.getWidth()/2))); 
		int y_pixel=(int) Math.round(((((yMax/2) + y)*screenHeight/yMax)-(pallino.getHeight()/2)));
		viewX.setLayoutParams(new LayoutParams(x_pixel, LayoutParams.MATCH_PARENT));
		viewY.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, y_pixel));
		
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
