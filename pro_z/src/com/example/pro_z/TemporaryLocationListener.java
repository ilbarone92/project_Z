package com.example.pro_z;

import java.util.Observable;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


public class TemporaryLocationListener extends Observable implements LocationListener {

	private double startLatitude;
	private double startLongitude;
	private boolean started = false;
	private Location location;
	
	public TemporaryLocationListener(GameActivity activity) {
		addObserver(activity);
	}
	
	public double getStartLatitude() {
		return startLatitude;
	}

	public double getStartLongitude() {
		return startLongitude;
	}

	
	private void setLocation(Location location){
		this.location=location;
	}

	public Location getLocation() {
		return location;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		if (!started) {
			startLatitude = location.getLatitude();
			startLongitude = location.getLongitude();
			started = true;
		}
		setLocation(location);
		notifyObservers();
		setChanged();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
}
