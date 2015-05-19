package com.example.pro_z.engine;

import java.io.File;
import java.util.HashMap;

import android.net.Uri;
import android.widget.ImageView;
/**
 * Questa classe modella una mappa di triangolazione, deve contenere 3 e soli {@link TriangulationPoint}
 * @author Davide
 *
 */
public class MapModel {

	private HashMap<String, TriangulationPoint> points;
	private ImageView imageView;
	
	public MapModel() {
		points = new HashMap<>();
	}
	
	public void addPoint(String name, TriangulationPoint point){
		//TODO permettere l'inserimento di n. 3 punti
		points.put(name, point);
	}
	
	public HashMap<String, TriangulationPoint> getPoints() {
		return points;
	}
	
	public void setImageView(String path) {
		File file = new File(path);
		Uri uri = Uri.fromFile(file);
		imageView.setImageURI(uri);
	}
	
	public ImageView getImageView() {
		return imageView;
	}
}
