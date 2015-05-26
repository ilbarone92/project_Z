package com.example.pro_z.engine;

import java.util.HashMap;
/**
 * Questa classe modella una mappa di triangolazione, deve contenere 3 e soli {@link TriangulationPoint}
 * @author Davide
 *
 */
public class MapModel {

	private HashMap<String, TriangulationPoint> points;
	private String mapName;
	
	public MapModel() {
		points = new HashMap<String,TriangulationPoint>();
	}
	
	public void addPoint(String name, TriangulationPoint point){
		//TODO permettere l'inserimento di n. 3 punti
		points.put(name, point);
	}
	
	public HashMap<String, TriangulationPoint> getPoints() {
		return points;
	}
	
	public void setMapKey(String mapName) {
		this.mapName= mapName;
	}
	
	public String getMapKey() {
		return mapName;
	}
}
