package com.example.pro_z.engine;

/**
 * Questa classe fornisce la struttura base per un punto di localizzazione, x e y sono le coordinate in pixel,
 * alle quali vengono fatte corrispondere le coordinate in latitudine e longitudine
 * @author Davide
 *
 */
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
