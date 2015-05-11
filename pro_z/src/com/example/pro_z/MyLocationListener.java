package com.example.pro_z;

import java.util.Observable;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
/**
 * Questa classe gestisce la localizzazione, in particolare il metodo onLocationChanged, implementato 
 * dall'interfaccia {@link LocationListener} notifica la vista quando viene rilevata una 
 * nuova posizione dal GPS per aggiornarla.
 * @author Davide
 *
 */
public class MyLocationListener extends Observable implements LocationListener {

	private static MyLocationListener listener;
	private double startLatitude;
	private double startLongitude;
	private boolean started = false;
	private Location location;
	
	
	
	/**
	 * Questo metodo notifica quando viene rilevata una nuova posizione, inoltre se è il
	 * primo rilevamento GPS imposta il flag {@started} a true
	 */
	
	
	private MyLocationListener(){
		
	}

	
	public static MyLocationListener getMyLocationListener(){
		if (listener == null) {
			listener = new MyLocationListener();
		}
		return listener;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		if (!started) {
			startLatitude = location.getLatitude();
			startLongitude = location.getLongitude();
			started = true;
		}
		setLocation(location);
		setChanged();
		notifyObservers();
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
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
	
	public double getStartLatitude() {
		return startLatitude;
	}
	
	public double getStartLongitude() {
		return startLongitude;
	}
}
