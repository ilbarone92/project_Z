package com.example.tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.example.pro_z.engine.MapEngine;
import com.example.pro_z.engine.MapModel;
import com.example.pro_z.engine.TriangulationPoint;
import com.example.pro_z.loading.MapLoader;

public class TestMaps {
	
	MapModel model;
	
	@Before public void initialize() {
		model = new MapModel();
		double lat1=45.204776;
		double lat2=45.204776;	//A e B han stessa latitudine
		double lat3=45.202906;
		double long1=9.134210;
		double long2=9.137102;
		double long3=9.134210;	//A e C han stessa longitudine
		model.addPoint("A", new TriangulationPoint(0, 640, lat1, long1));
		model.addPoint("B", new TriangulationPoint(480, 640, lat2, long2));
		model.addPoint("C", new TriangulationPoint(480, 0, lat3, long3));
	}
	
	@Test
	public void testMap() {
		assertNotNull("Missing class LocalizationMap", model);
	}
	
	@Test
	public void testMapEngineClass() {
		assertNotNull("Missing class MapEngine", new MapEngine(model));
	}

	//Test caricamento Mappa
	@Test (expected = NoClassDefFoundError.class)
	public void testLoadNewMap() throws IOException {
		MapLoader loader = new MapLoader(null);
		//MapEngine map = new MapEngine(model);
		//	map.calculateCoefficients(loader.load("La Nave", 800, 600));
			loader.load("La Nave", 800, 600);
	}
	
	@Test
	public void testLoadNewMapFromConstructor() {
		//assertNotNull(new MapEngine());
	}
		
	@Test
	public void testToPixelCoordinate(){

		MapEngine engine = new MapEngine(model);
		
//		//punto centrale della mappa
		int coordinate[] = engine.calculatePixelCordinate(45.203841, 9.135656);
		
		//testa la precisione della conversione con errore di 1px
		int delta = 1;
		//il test riesce se la conversione restituisce la posizione centrale dello schermo
		assertTrue(coordinate[0]<=240+delta && coordinate[0]>=240-delta );
		assertTrue(coordinate[1]<=320+delta && coordinate[1]>=320-delta );
	}


}
