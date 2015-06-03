package com.example.pro_z.engine;


public class MapEngine {

	
	private double[] coefficients = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0,};	//a,b,c,d,e,f
	
	public MapEngine(MapModel model) {
		calculateCoefficients(model);
	}
	
	private void calculateCoefficients(MapModel map){
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
		
	}
	
	
	
	public int[] calculatePixelCordinate(double latitude, double longitude) {
		int[] coordinate = {0,0};
		Integer xp = (int) (coefficients[0]*longitude+coefficients[4]);
		Integer yp = (int)(coefficients[3]*latitude+coefficients[5]);
		coordinate[0] = xp.intValue();
		coordinate[1] = yp.intValue();
		return coordinate; 
	}

}