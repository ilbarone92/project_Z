package com.example.pro_z;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;


public class MyLocationListener implements LocationListener {

	private static final int CIRC_EQUATORE = 40075040;
	private static final int CIRC_MERIDIANO = 40009000;

	private int screenWidth;
	private int screenHeight;
	private int xMax;
	private float yMax;
	private double startLatitude;
	private double startLongitude;
	private boolean started = false;
	
	private View viewY;
	private View viewX;
	private ImageView pallino;
	
	public MyLocationListener(Display display, ImageView pallino, View viewX, View viewY) {
		screenWidth = display.getWidth();
		screenHeight = display.getHeight();
		
		xMax = 200;
		yMax = xMax * (screenHeight / screenWidth);
		
		this.pallino = pallino;
		this.viewX = viewX;
		this.viewY = viewY;

		settaCoordinate((screenWidth / 2) - (pallino.getWidth() / 2),(screenHeight / 2) - (pallino.getHeight() / 2));

	}
	
	public void updateGUI(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		
		//calcolo di x e y in termini di metri
		double x = (longitude - startLongitude)*CIRC_EQUATORE/180;
		double y = (latitude - startLatitude)*CIRC_MERIDIANO/90;
		
		//calcolo della x e y in termini di pixel
		int x_pixel=(int) Math.round(((((xMax/2) + x)*screenWidth/xMax)-(pallino.getWidth()/2))); 
		int y_pixel=(int) Math.round(((((yMax/2) + y)*screenHeight/yMax)-(pallino.getHeight()/2)));

		settaCoordinate(x_pixel, y_pixel);
		
	}
	
	private void settaCoordinate(int x, int y) {
		viewY.getLayoutParams().height = y;
		viewY.setLayoutParams(viewY.getLayoutParams());
		viewX.getLayoutParams().width = x;
		viewX.setLayoutParams(viewX.getLayoutParams());
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
