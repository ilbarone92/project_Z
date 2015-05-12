package com.example.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.example.pro_z.engine.LocalizationMap;
import com.example.pro_z.engine.MapEngine;
import com.example.pro_z.engine.TriangulationPoint;
import com.example.pro_z.loading.MapLoader;

public class TestMaps {

	@Test
	public void testMapEngineClass() {
		assertNotNull("Missing class MapEngine", new MapEngine());
	}
	
	@Test
	public void testMap() {
		assertNotNull("Missing class LocalizationMap", new LocalizationMap());
	}
	//Test caricamento Mappa
	@Test
	public void testLoadNewMap() {
		MapLoader loader = new MapLoader();
		MapEngine map = new MapEngine();
		try {
			map.calculateCoefficients(loader.load("path"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		//In realtï¿œ  i primi due parametri potranno valere 0, Display.getHeight, Display.getWidth
		locMap.addPoint("A", new TriangulationPoint(0, 640, lat1, long1));
		locMap.addPoint("B", new TriangulationPoint(480, 640, lat2, long2));
		locMap.addPoint("C", new TriangulationPoint(480, 0, lat3, long3));
		map.calculateCoefficients(locMap);
		for (int i = 0; i < map.getCoefficients().length; i++) {
			System.out.println("coefficiente "+i+": "+map.getCoefficients()[i]);
		}
	}
		
	@Test
	public void testToPixelCoordinate(){
		MapEngine map = new MapEngine();
		LocalizationMap locMap = new LocalizationMap();
		double lat1=45.204776;
		double lat2=45.204776;	//A e B han stessa latitudine
		double lat3=45.202906;
		double long1=9.134210;
		double long2=9.137102;
		double long3=9.134210;	//A e C han stessa longitudine
		//In realtà  i primi due parametri potranno valere 0, Display.getHeight, Display.getWidth
		locMap.addPoint("A", new TriangulationPoint(0, 640, lat1, long1));
		locMap.addPoint("B", new TriangulationPoint(480, 640, lat2, long2));
		locMap.addPoint("C", new TriangulationPoint(480, 0, lat3, long3));
		map.calculateCoefficients(locMap);
		
		//punto centrale della mappa
		int coordinate[] = map.calculatePixelCordinate(45.203841, 9.135656);
		
		//testa la precisione della conversione con errore di 1px
		int delta = 1;
		//il test riesce se la conversione restituisce la posizione centrale dello schermo
		assertTrue(coordinate[0]<=240+delta && coordinate[0]>=240-delta );
		assertTrue(coordinate[1]<=320+delta && coordinate[1]>=320-delta );
	}


}
