package com.example.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.pro_z.LocalizationMap;
import com.example.pro_z.MapEngine;
import com.example.pro_z.TriangulationPoint;

public class TestMaps {

	@Test
	public void testMapEngineClass() {
		assertNotNull("Missing class MapEngine", new MapEngine());
	}
	
	@Test
	public void testMap() {
		assertNotNull("Missing class LocalizationMap", new LocalizationMap());
	}
	
	@Test
	public void testLoadNewMap() {
		LocalizationMap map = new LocalizationMap();
		MapEngine mapEngine = new MapEngine();
		assertTrue(mapEngine.loadMap(map));
	}
	
	@Test
	public void testLoadNewMapFromConstructor() {
		assertNotNull(new MapEngine());
	}

	@Test 
	public void testCalculateCoefficients(){
		MapEngine map = new MapEngine();
		LocalizationMap locMap = new LocalizationMap();
		double lat1=45.204776;
		double lat2=45.204776;	//A e B han stessa latitudine
		double lat3=45.202906;
		double long1=9.134210;
		double long2=9.137102;
		double long3=9.134210;	//A e C han stessa longitudine
		//In realtà  i primi due parametri potranno valere 0, Display.getHeight, Display.getWidth
		locMap.addPoint("A", new TriangulationPoint(0, 0, lat1, long1));
		locMap.addPoint("B", new TriangulationPoint(0, 640, lat2, long2));
		locMap.addPoint("C", new TriangulationPoint(480, 640, lat3, long3));
		map.calculateCoefficients(locMap);
		for (int i = 0; i < map.getCoefficients().length; i++) {
			System.out.println(map.getCoefficients()[i]);
		}
	}
//	@Test
//	public void testGPSConversion() {
//		MapEngine mapEngine = new MapEngine();
//		mapEngine.getPixelCordinate();
//
//	}

}
