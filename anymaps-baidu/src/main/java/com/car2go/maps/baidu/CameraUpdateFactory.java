/*
 * Copyright (c) 2017 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.baidu;

import com.car2go.maps.CameraUpdate;
import com.car2go.maps.baidu.model.BaiduCameraUpdate;
import com.car2go.maps.model.LatLng;
import com.car2go.maps.model.LatLngBounds;

/**
 * Creates {@link CameraUpdate} objects which can be used to update map camera
 */
public class CameraUpdateFactory implements com.car2go.maps.CameraUpdateFactory {

	private static final CameraUpdateFactory instance = new CameraUpdateFactory();

	private CameraUpdateFactory() {
	}

	public static CameraUpdateFactory getInstance() {
		return instance;
	}

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
