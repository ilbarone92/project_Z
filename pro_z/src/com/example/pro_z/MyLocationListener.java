package com.example.pro_z;

import java.util.Observable;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
/**
 * Questa classe gestisce la localizzazione, in particolare il metodo onLocationChanged, implementato 
 * dall'interfaccia {@link LocationListener} notifica la vista, {@link MapView}, quando viene rilevata una 
 * nuova posizione dal GPS per aggiornarla.
 * @author Davide
 *
 */
public class MyLocationListener extends Observable implements LocationListener {

//	private static final int CIRC_EQUATORE = 40075040;
//	private static final int CIRC_MERIDIANO = 40009000;
//
//	private int screenWidth;
//	private int screenHeight;
//	private int xMax;
//	private float yMax;
	private double startLatitude;
	private double startLongitude;
	private boolean started = false;
	private Location location;
//	
//	private View viewY;
//	private View viewX;
//	private ImageView pallino;
	
	public MyLocationListener(MapView view) {
//	public MyLocationListener(Display display, ImageView pallino, View viewX, View viewY, MapView view) {
//		screenWidth = display.getWidth();
//		screenHeight = display.getHeight();
//		
//		xMax = 200;
//		yMax = xMax * (screenHeight / screenWidth);
//		
//		this.pallino = pallino;
//		this.viewX = viewX;
//		this.viewY = viewY;
//
//		setWidth((screenWidth / 2) - (pallino.getWidth() / 2));
//		setHeight((screenHeight / 2) - (pallino.getHeight() / 2));
//		
		addObserver(view);
	}
	
//	private void setHeight(int y) {
//		viewY.getLayoutParams().height = y;
//		viewY.setLayoutParams(viewY.getLayoutParams());
//	}
//
//	private void setWidth(int x) {
//		viewX.getLayoutParams().width = x;
//		viewX.setLayoutParams(viewX.getLayoutParams());
//	}
	/**
	 * Questo metodo notifica {@link MapView} quando viene rilevata una nuova posizione, inoltre se è il
	 * primo rilevamento GPS imposta il flag {@started} a true
	 */
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
