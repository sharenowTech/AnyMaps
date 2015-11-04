/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.baidu;

import net.doo.maps.CameraUpdate;
import net.doo.maps.baidu.model.BaiduCameraUpdate;
import net.doo.maps.model.LatLng;
import net.doo.maps.model.LatLngBounds;

/**
 * Creates {@link CameraUpdate} objects which can be used to update map camera
 */
public class CameraUpdateFactory implements net.doo.maps.CameraUpdateFactory {

	@Override
	public CameraUpdate newLatLngZoom(LatLng center, float zoomLevel) {
		return new BaiduCameraUpdate.Builder()
				.center(center)
				.zoom(zoomLevel)
				.build();
	}

	@Override
	public CameraUpdate newLatLngBounds(LatLngBounds bounds, int padding) {
		return new BaiduCameraUpdate.Builder()
				.bounds(bounds)
				.padding(padding)
				.build();
	}

	@Override
	public CameraUpdate zoomTo(float zoomLevel) {
		return new BaiduCameraUpdate.Builder()
				.zoom(zoomLevel)
				.build();
	}

}
