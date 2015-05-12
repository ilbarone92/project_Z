package com.example.pro_z.engine;

import java.util.Observable;
import java.util.Observer;

import android.location.Location;
import android.location.LocationListener;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
/**
 * Questa classe rappresenta la posizione dell'utente, osservando i cambiamenti di {@link MyLocationListener};
 * 
 * @author Davide
 *
 */
public class MapView implements Observer {
	
	private static final int CIRC_EQUATORE = 40075040;
	private static final int CIRC_MERIDIANO = 40009000;

	private int screenWidth;
	private int screenHeight;
	private final int xMax=200;
	private float yMax;
	private View viewY;
	private View viewX;
	private ImageView pallino;
	private MyLocationListener locationListener;
	
	public MapView(Display display, ImageView pallino, View viewX, View viewY) {
		screenWidth = display.getWidth();
		screenHeight = display.getHeight();
		
		yMax = xMax * (screenHeight / screenWidth);
		
		this.pallino = pallino;
		this.viewX = viewX;
		this.viewY = viewY;

		setWidth((screenWidth / 2) - (pallino.getWidth() / 2));
		setHeight((screenHeight / 2) - (pallino.getHeight() / 2));
		
		locationListener = MyLocationListener.getMyLocationListener();
	}
	/**
	 * updateGUI aggiorna l'immagine della mappa ricalcolando le posizione delle viewX e viewY, 
	 * che di conseguenza fanno spostare il pallino nella giusta posizione rispetto al centro
	 * dello schermo, che è il punto di partenza.
	 * @param location
	 */
	public void updateGUI(Location location) {
		double latitude = location.getLatitude();
		double longitude = location.getLongitude();
		
		//calcolo di x e y in termini di metri
		double x = (longitude - locationListener.getStartLongitude())*CIRC_EQUATORE/180;
		double y = (latitude - locationListener.getStartLatitude())*CIRC_MERIDIANO/90;
		
		//calcolo della x e y in termini di pixel
		int x_pixel=(int) Math.round(((((xMax/2) + x)*screenWidth/xMax)-(pallino.getWidth()/2))); 
		int y_pixel=(int) Math.round(((((yMax/2) + y)*screenHeight/yMax)-(pallino.getHeight()/2)));

		setHeight(y_pixel);
		setWidth(x_pixel);
		
	}
	/**
	 * Questo metodo � un override del metodo update di {@link Observer}, il quale ricevuta la notifica
	 * del cambiamento di posizione nel metodo onLocationChanghed di {@link MyLocationListener}
	 * lancia l'aggiornamento della GUI
	 */
	@Override
	public void update(Observable observable, Object data) {
		updateGUI(locationListener.getLocation());
	}

	private void setHeight(int y) {
		viewY.getLayoutParams().height = y;
		viewY.setLayoutParams(viewY.getLayoutParams());
	}

	private void setWidth(int x) {
		viewX.getLayoutParams().width = x;
		viewX.setLayoutParams(viewX.getLayoutParams());
	}
	/**
	 * Ritorna il {@link LocationListener} relativo a questa mappa
	 * @return
	 */
	public MyLocationListener getLocationListener() {
		return locationListener;
	}

}
