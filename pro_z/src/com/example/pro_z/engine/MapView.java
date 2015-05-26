package com.example.pro_z.engine;

import java.util.Observable;
import java.util.Observer;

import android.location.LocationListener;
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

	public MapView(Display display, ImageView player) {
		this.player = player;

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

	}
	

}
