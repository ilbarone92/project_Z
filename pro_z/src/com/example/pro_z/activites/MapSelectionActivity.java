package com.example.pro_z.activites;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pro_z.R;

public class MapSelectionActivity extends Activity {
	private Intent intent;
	private ArrayAdapter<String> adapter;
	private ListView listaMappe;
	private ArrayList<String> mappe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_selection);

		// TODO creare vettore nomi mappe
		String mappa = new String("La Nave");

		mappe = new ArrayList<String>();
		mappe.add(mappa);

		listaMappe = (ListView) findViewById(R.id.listView1);

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mappe);
		intent = new Intent(this, SplashActivity.class);
		listaMappe.setAdapter(adapter);
		listaMappe.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				intent.putExtra("mapName", mappe.get(position));
				startActivity(intent);

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_selection, menu);
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
