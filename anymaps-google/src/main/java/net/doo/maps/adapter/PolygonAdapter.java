/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter;

import net.doo.maps.model.LatLng;
import net.doo.maps.model.Polygon;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapts Google Polygon to AnyMap Polygon
 */
public class PolygonAdapter implements Polygon {

	private final com.google.android.gms.maps.model.Polygon polygon;

	public PolygonAdapter(com.google.android.gms.maps.model.Polygon polygon) {
		this.polygon = polygon;
	}

	@Override
	public void setHoles(List<List<LatLng>> holes) {
		polygon.setHoles(mapHoles(holes));
	}

	@Override
	public List<LatLng> getPoints() {
		return AnyMapAdapter.adaptList(com.google.android.gms.maps.model.LatLng.class, polygon.getPoints());
	}

	private List<? extends List<com.google.android.gms.maps.model.LatLng>> mapHoles(List<List<LatLng>> holes) {
		ArrayList<List<com.google.android.gms.maps.model.LatLng>> result = new ArrayList<>();

		for (List<LatLng> hole : holes) {
			List<com.google.android.gms.maps.model.LatLng> googleHole = AnyMapAdapter.adaptList(LatLng.class, hole);

			result.add(googleHole);
		}

		return result;
	}

	@Override
	public void setVisible(boolean visible) {
		polygon.setVisible(visible);
	}

	@Override
	public void remove() {
		polygon.remove();
	}

}
