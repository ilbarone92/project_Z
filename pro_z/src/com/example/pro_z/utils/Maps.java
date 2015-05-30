package com.example.pro_z.utils;

import java.util.HashMap;

import com.example.pro_z.R;

public class Maps {

	private static Maps MAPS = new Maps();
	private HashMap<String, Integer> maps = new HashMap<String, Integer>();
	
	
	private Maps() {
		maps.put("La Nave", R.drawable.map01);
		maps.put("La Nave2", R.drawable.map01);
		maps.put("La Nave3", R.drawable.map01);
		maps.put("La Nave4", R.drawable.map01);
		
		}
	
	public static Maps get() {
		return MAPS;
	}
	
	public HashMap<String, Integer> getMaps() {
		return maps;
	}
}
