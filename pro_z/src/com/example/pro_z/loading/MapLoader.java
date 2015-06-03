package com.example.pro_z.loading;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.pro_z.engine.MapModel;
import com.example.pro_z.engine.TriangulationPoint;

public class MapLoader {

	public static final String MAPS = "Maps.xml";
	private AssetManager manager;

	public MapLoader(Context context) {
		manager = context.getAssets();
	}

	public MapModel load(String mapName, int screenWidth, int screenHeight) throws IOException {

		MapModel mapModel;

		InputStream is = manager.open(MAPS);

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document doc = builder.parse(is);

			NodeList mapsList = doc.getElementsByTagName("map");

			double aLat = 0;
			double aLong = 0;
			double bLat = 0;
			double bLong = 0;
			double cLat = 0;
			double cLong = 0;

			for (int i = 0; i < mapsList.getLength(); i++) {
				Node map = mapsList.item(i);
				NodeList mapInfo = map.getChildNodes();
				boolean found = false;
				for (int j = 0; j < mapInfo.getLength(); j++) {

					Node info = mapInfo.item(j);
					String nodeName = info.getNodeName();
					String textContent = info.getTextContent();
					if (found) {
						if (nodeName.equals("A_lat")) {
							aLat = Double.parseDouble(textContent);
						} else if (nodeName.equals("A_long")) {
							aLong = Double.parseDouble(textContent);
						} else if (nodeName.equals("B_lat")) {
							bLat = Double.parseDouble(textContent);
						} else if (nodeName.equals("B_long")) {
							bLong = Double.parseDouble(textContent);
						} else if (nodeName.equals("C_lat")) {
							cLat = Double.parseDouble(textContent);
						} else if (nodeName.equals("C_long")) {
							cLong = Double.parseDouble(textContent);
						}
					} else if (nodeName.equals("map_name") && textContent.equals(mapName)) {
						found = true;
					}
				}
			}
			
			HashMap<String, TriangulationPoint> mappaPunti = new HashMap<String, TriangulationPoint>();
			mappaPunti.put("A", new TriangulationPoint(0, screenHeight, aLat, aLong));
			mappaPunti.put("B", new TriangulationPoint(screenWidth, screenHeight, bLat, bLong));
			mappaPunti.put("C", new TriangulationPoint(0, 0, cLat, cLong));
			
			mapModel = new MapModel(mappaPunti);
			
			mapModel.setMapKey(mapName);
			
			return mapModel;

		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return null;
	}

}
