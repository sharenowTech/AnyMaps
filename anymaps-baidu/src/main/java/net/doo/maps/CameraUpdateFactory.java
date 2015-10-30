/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import net.doo.maps.model.LatLng;
import net.doo.maps.model.LatLngBounds;

/**
 * Creates {@link CameraUpdate} objects which can be used to update map camera
 */
public class CameraUpdateFactory {

	/**
	 * @return {@link CameraUpdate} which moves camera to given position with given zoom level.
	 */
	public static CameraUpdate newLatLngZoom(LatLng center, float zoomLevel) {
		return new BaiduCameraUpdate.Builder()
				.center(center)
				.zoom(zoomLevel)
				.build();
	}

	/**
	 * @return {@link CameraUpdate} which moves camera so it displays given bounds with given padding.
	 */
	public static CameraUpdate newLatLngBounds(LatLngBounds bounds, int padding) {
		return new BaiduCameraUpdate.Builder()
				.bounds(bounds)
				.padding(padding)
				.build();
	}

	/**
	 * @return {@link CameraUpdate} which zooms camera to given zoom level.
	 */
	public static CameraUpdate zoomTo(float zoomLevel) {
		return new BaiduCameraUpdate.Builder()
				.zoom(zoomLevel)
				.build();
	}

}
