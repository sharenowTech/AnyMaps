/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.osm;

import net.doo.maps.AnyMap;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

/**
 * Handles MyLocation feature
 */
class MyLocationHandler {

	private final MyLocationNewOverlay myLocationOverlay;

	public MyLocationHandler(MapView map) {
		myLocationOverlay = new MyLocationNewOverlay(map.getContext(), map);
		map.getOverlays().add(myLocationOverlay);
	}

	/**
	 * @see AnyMap#setMyLocationEnabled(boolean)
	 */
	public void setMyLocationEnabled(boolean enabled) {
		if (enabled) {
			myLocationOverlay.enableMyLocation();
		} else {
			myLocationOverlay.disableMyLocation();
		}
	}

}
