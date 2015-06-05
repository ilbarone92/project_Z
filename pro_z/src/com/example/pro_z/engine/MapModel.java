package com.example.pro_z.engine;

import java.util.HashMap;
/**
 * Questa classe modella una mappa di triangolazione,che deve contenere 3 e soli {@link TriangulationPoint}, e inoltre calcola in un primo
 *  momento i coefficienti della trasformazione lineare che convertono qualsiasi coodinata geografica in una posizione sullo schermo
 * 
 * @author Davide
 *
 */
public class MapModel {

	private double[] coefficients = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0,};	//a,b,c,d,e,f
	private HashMap<String, TriangulationPoint> points;
	private String mapName;
	
	public MapModel(HashMap<String, TriangulationPoint> points) {
		this.points = points;
		calculateCoefficients();
	}
	
	public void addPoint(String name, TriangulationPoint point){
		points.put(name, point);
	}
	/**
	 * Questo metodo risolve il sistema lineare per calcolare i coefficienti necessari alla conversione posizione geografica-pozizione in pixel
	 */
	private void calculateCoefficients(){
		int  x_ap=points.get("A").getX();
		int  x_bp=points.get("B").getX();
		double  x_agps=points.get("A").getLongitude();
		double  x_bgps=points.get("B").getLongitude();
		
		coefficients[0]=(x_ap - x_bp) / (x_agps - x_bgps);			//calcolo di 'a'
		coefficients[4]= x_bp  - coefficients[0]*x_bgps;			//calcolo di 'e'		
		
		int y_ap = points.get("A").getY();
		int y_cp = points.get("C").getY();	
		double  y_agps=points.get("A").getLatitude();
		double  y_cgps=points.get("C").getLatitude();
		
		coefficients[3] = (y_cp - y_ap) / (y_cgps - y_agps);		//calcolo di 'd'
		coefficients[5]= y_cp  - coefficients[3]*y_cgps;			//calcolo di 'f'
		
	}
	
	public double[] getCoefficients() {
		return coefficients;
	}
	
	public void setMapKey(String mapName) {
		this.mapName= mapName;
	}
	
	public String getMapKey() {
		return mapName;
	}
}
