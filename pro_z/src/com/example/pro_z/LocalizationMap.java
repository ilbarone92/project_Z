package com.example.pro_z;

import java.util.HashMap;

public class LocalizationMap {

	private HashMap<String, TriangulationPoint> points;
	
	public LocalizationMap() {
	}
	
	public void addPoint(String name, TriangulationPoint point){
		//TODO permettere l'inserimento di n. 3 punti
		points.put(name, point);
	}
	
	public HashMap<String, TriangulationPoint> getPoints() {
		return points;
	}
}
