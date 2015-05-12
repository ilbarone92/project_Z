package com.example.pro_z.loading;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.example.pro_z.engine.LocalizationMap;
import com.example.pro_z.engine.TriangulationPoint;

public class MapLoader {

	private static final String MAPS = "maps/Maps.xml";

	public MapLoader() {
		// TODO Auto-generated constructor stub
	}

	public LocalizationMap load(String mapName) throws IOException {

		LocalizationMap mapModel = new LocalizationMap();
		File fileXML = new File(MAPS);

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			Document doc = builder.parse(fileXML);

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
			
			//TODO recuperare x e y per i tre punti
			mapModel.addPoint("A", new TriangulationPoint(0, 0, aLat, aLong));
			mapModel.addPoint("B", new TriangulationPoint(0, 0, bLat, bLong));
			mapModel.addPoint("C", new TriangulationPoint(0, 0, cLat, cLong));
			
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		return mapModel;
	}

}
