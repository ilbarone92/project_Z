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
	private MapModel model;
	private Display display;

	public MapView(Display display, ImageView player, MapModel model) {
		this.player = player;
		this.model = model;
		this.display = display;
		locationListener = MyLocationListener.getMyLocationListener();
	}

	/**
	 * Questo metodo � un override del metodo update di {@link Observer}, il
	 * quale ricevuta la notifica del cambiamento di posizione nel metodo
	 * onLocationChanghed di {@link MyLocationListener} aggiorna l'indicatore della posizione del giocatore 
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void update(Observable observable, Object data) {
		Location location = locationListener.getLocation();
		int[] pixelCoordinates = calculatePixelCordinate(location.getLatitude(), location.getLongitude());
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
	/**
	 * Questo metodo effettua la conversione posizione geografica-posizione in pixel prendendo i coefficienti da {@link MapModel}
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public int[] calculatePixelCordinate(double latitude, double longitude) {
		int[] coordinate = {0,0};
		Integer xp = (int) (model.getCoefficients()[0]*longitude+model.getCoefficients()[4]);
		Integer yp = (int)(model.getCoefficients()[3]*latitude+model.getCoefficients()[5]);
		coordinate[0] = xp.intValue();
		coordinate[1] = yp.intValue();
		return coordinate; 
	}

}
