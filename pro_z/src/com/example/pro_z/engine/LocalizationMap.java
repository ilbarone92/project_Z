package com.example.pro_z.engine;

import java.util.HashMap;
import java.util.TreeMap;
/**
 * Questa classe modella una mappa di triangolazione, deve contenere 3 e soli {@link TriangulationPoint}
 * @author Davide
 *
 */
public class LocalizationMap {

	private HashMap<String, TriangulationPoint> points;
	
	public LocalizationMap() {
		points = new HashMap<>();
	}
	
	public void addPoint(String name, TriangulationPoint point){
		//TODO permettere l'inserimento di n. 3 punti
		points.put(name, point);
	}
	
	public HashMap<String, TriangulationPoint> getPoints() {
		return points;
	}
}
