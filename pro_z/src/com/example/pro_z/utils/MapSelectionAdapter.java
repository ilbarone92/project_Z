package com.example.pro_z.utils;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pro_z.R;
/**
 * 
 * @author Davide
 *
 */
public class MapSelectionAdapter extends BaseAdapter {

	private HashMap<String, Integer> maps;
	private Context context;
	
	public MapSelectionAdapter(Context context, HashMap<String, Integer> maps) {
		super();
		this.maps = maps;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		return maps.size();
	}

	@Override
	public Object getItem(int position) {
		Set<Entry<String, Integer>> set = maps.entrySet();
		Object[] entries = set.toArray();
		return entries[position];
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).hashCode();
	}

	@SuppressWarnings("unchecked")
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.map_selection_view, null);
		}
		
		Entry<String, Integer> map = (Entry<String, Integer>) getItem(position);
		
		ImageView iv = (ImageView) convertView.findViewById(R.id.map_image_view);
		iv.setImageResource(map.getValue());

		
		TextView tv = (TextView) convertView.findViewById(R.id.map_text_view);
		tv.setText(map.getKey());
		
		
		return convertView;
	}

}
