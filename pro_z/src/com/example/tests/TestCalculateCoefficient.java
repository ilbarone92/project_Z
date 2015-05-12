package com.example.tests;

import com.example.pro_z.engine.LocalizationMap;
import com.example.pro_z.engine.MapEngine;
import com.example.pro_z.engine.TriangulationPoint;

public class TestCalculateCoefficient {

	public static void main(String[] args) {
		
		MapEngine map = new MapEngine();
		LocalizationMap locMap = new LocalizationMap();
		double lat1=45.204776;
		double lat2=45.204776;	//A e B han stessa latitudine
		double lat3=45.202906;
		double long1=9.134210;
		double long2=9.137102;
		double long3=9.134210;	//A e C han stessa longitudine
		//In realt�  i primi due parametri potranno valere 0, Display.getHeight, Display.getWidth
		locMap.addPoint("A", new TriangulationPoint(0, 0, lat1, long1));
		locMap.addPoint("B", new TriangulationPoint(0, 640, lat2, long2));
		locMap.addPoint("C", new TriangulationPoint(480, 640, lat3, long3));
		map.calculateCoefficients(locMap);
		for (int i = 0; i < map.getCoefficients().length; i++) {
			System.out.println(map.getCoefficients()[i]);
		}
	}
}
