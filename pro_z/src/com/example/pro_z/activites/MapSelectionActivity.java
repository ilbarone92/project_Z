package com.example.pro_z.activites;

import java.util.HashMap;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.example.pro_z.R;
import com.example.pro_z.utils.MapSelectionAdapter;
import com.example.pro_z.utils.Maps;

public class MapSelectionActivity extends Activity {
	private Intent intent;
	private MapSelectionAdapter adapter;
	private GridView elencoMappe;
	private HashMap<String, Integer> mappe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_selection);

		mappe = Maps.get().getMaps();
		
		elencoMappe = (GridView) findViewById(R.id.grid_view);

		adapter = new MapSelectionAdapter(this, mappe);
		
		elencoMappe.setAdapter(adapter);
		elencoMappe.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				Entry<String, Integer> map = (Entry<String, Integer>)adapter.getItem(position);
				String mapName = map.getKey();
				intent = new Intent(MapSelectionActivity.this, SplashActivity.class);
				intent.putExtra("mapName", mapName);
				startActivity(intent);

			}

		});
	}
}
