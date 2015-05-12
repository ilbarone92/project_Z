package com.example.pro_z;

import java.util.HashMap;

import android.location.Location;

public class MapEngine {

	
	private double[] coefficients;							//a,b,c,d,e,f
	LocalizationMap map = new LocalizationMap();
	
	
	public MapEngine() {
	
	}
	
	public boolean loadMap(LocalizationMap map) {
		return true;
	}
	
	public void calculateCoefficients(LocalizationMap map){
		int  x_ap=map.getPoints().get("A").getX();
		int  x_bp=map.getPoints().get("B").getX();
		double  x_agps=map.getPoints().get("A").getLongitude();
		double  x_bgps=map.getPoints().get("B").getLongitude();
		
		coefficients[0]=(x_ap - x_bp) / (x_agps - x_bgps);			//calcolo di 'a'
		coefficients[4]= x_bp  - coefficients[0]*x_bgps;			//calcolo di 'e'		
		
		int y_ap = map.getPoints().get("A").getY();
		int y_cp = map.getPoints().get("C").getY();	
		double  y_agps=map.getPoints().get("A").getLatitude();
		double  y_cgps=map.getPoints().get("C").getLatitude();
		
		coefficients[3] = (y_cp - y_ap) / (y_cgps - y_agps);		//calcolo di 'd'
		coefficients[5]= y_cp  - coefficients[3]*y_cgps;			//calcolo di 'f'
		System.out.println(coefficients[5]);
		System.out.println(y_ap  - coefficients[3]*y_agps);
	}
	
	public double[] getCoefficients() {
		return coefficients;
	}

//	public int[] calculatePixelCordinate(double latitude, double longitude) {
//		
//		return; 
//	}

}
