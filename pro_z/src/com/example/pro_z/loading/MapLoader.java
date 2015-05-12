package com.example.pro_z.loading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import com.example.pro_z.engine.LocalizationMap;

public class MapLoader {
	
	
	public MapLoader() {
		// TODO Auto-generated constructor stub
	}
	
	public LocalizationMap load(String path) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(new File(path)));
		LocalizationMap mapModel;
		String line = in.readLine();
		while(line!=null){
			//TODO: dependig by format
		}
		in.close();
		return mapModel;
	}

}
