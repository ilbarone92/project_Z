package com.example.pro_z.engine;

import android.location.Location;
import android.view.Display;

public class TriangulationPoint {
	
	private int x,y;
	private double latitude, longitude;
	
	public TriangulationPoint(int x, int y, double latitude, double longitude) {
		super();
		this.x = x;
		this.y = y;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	

}
