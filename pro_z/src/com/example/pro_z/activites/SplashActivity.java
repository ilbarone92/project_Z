package com.example.pro_z.activites;

import java.util.Observable;
import java.util.Observer;

import com.example.pro_z.R;
import com.example.pro_z.R.id;
import com.example.pro_z.R.layout;
import com.example.pro_z.R.menu;
import com.example.pro_z.engine.MyLocationListener;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SplashActivity extends Activity implements Observer {

	private LocationManager manager;
	String locationProvider = LocationManager.GPS_PROVIDER;
	private static final int MIN_TIME = 10000;
	private static final int MIN_DISTANCE = 20;
	private MyLocationListener listener = MyLocationListener.getMyLocationListener();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		findViewById(R.id.mainSpinner1).setVisibility(View.VISIBLE);
		
		listener.addObserver(this);
		manager = (LocationManager) getSystemService(LOCATION_SERVICE);
		manager.requestLocationUpdates(locationProvider, MIN_TIME,
				MIN_DISTANCE, listener);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void update(Observable observable, Object data) {
		startActivity(new Intent(SplashActivity.this, GameActivity.class));
		listener.deleteObserver(this);
		finish();
		
	}
}