package com.example.pro_z.engine;

import java.util.Observable;
import java.util.Observer;

import android.location.Location;
import android.view.Display;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

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
	private Display display;

	public MapView(Display display, ImageView player, MapEngine engine) {
		this.player = player;
		this.engine = engine;
		this.display = display;
		locationListener = MyLocationListener.getMyLocationListener();
	}

	/**
	 * Questo metodo � un override del metodo update di {@link Observer}, il
	 * quale ricevuta la notifica del cambiamento di posizione nel metodo
	 * onLocationChanghed di {@link MyLocationListener} lancia l'aggiornamento
	 * della GUI
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void update(Observable observable, Object data) {
		Location location = locationListener.getLocation();
		int[] pixelCoordinates = engine.calculatePixelCordinate(location.getLatitude(), location.getLongitude());
		for (int i : pixelCoordinates) {
			if (i<0) {
				i=0;
			}
		}
		if (pixelCoordinates[0]>display.getWidth()-player.getWidth()) {
			pixelCoordinates[0] = display.getWidth()-player.getWidth();
		}else if (pixelCoordinates[1] > display.getHeight()-player.getHeight()) {
			pixelCoordinates[1] = display.getHeight()-player.getHeight();
		}
		LayoutParams params = (LayoutParams)player.getLayoutParams();
		params.setMargins(pixelCoordinates[0], 0, 0, pixelCoordinates[1]);
		player.setLayoutParams(params);
	}

}
