package com.example.pro_z.engine;

import java.util.Observable;
import java.util.Observer;

import android.location.Location;
import android.view.Display;
import android.widget.ImageView;

/**
 * Questa classe rappresenta la posizione dell'utente, osservando i cambiamenti
 * di {@link MyLocationListener};
 * 
 * @author Davide
 *
 */
public class MapView implements Observer {

	private ImageView player;
	private MyLocationListener locationListener;
	private MapEngine engine;
	public MapView(Display display, ImageView player, MapEngine engine) {
		this.player = player;
		this.engine = engine;
		locationListener = MyLocationListener.getMyLocationListener();
	}

	/**
	 * Questo metodo � un override del metodo update di {@link Observer}, il
	 * quale ricevuta la notifica del cambiamento di posizione nel metodo
	 * onLocationChanghed di {@link MyLocationListener} lancia l'aggiornamento
	 * della GUI
	 */
	@Override
	public void update(Observable observable, Object data) {
		Location location = locationListener.getLocation();
		
	}
	

}
