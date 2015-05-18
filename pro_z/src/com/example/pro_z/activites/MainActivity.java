package com.example.pro_z.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.pro_z.R;

/**
 * Questa Activity fornisce una interfaccia utente d'avvio molto semplice, con
 * un bottone per far partire il gioco, lanciando l'activity
 * {@link GameActivity}
 * 
 * @author Davide
 *
 */
public class MainActivity extends Activity {

	private Button button_inizia;
	private Button button_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button_inizia = (Button) findViewById(R.id.button_inizia);
		button_login = (Button) findViewById(R.id.button_login);
		button_inizia.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				startActivity(new Intent(MainActivity.this,
						SplashActivity.class));
			}
		});
		button_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				startActivity(new Intent(MainActivity.this, LoginActivity.class));
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
