/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google;

import com.car2go.maps.CameraUpdate;
import com.car2go.maps.google.adapter.AnyMapAdapter;
import com.car2go.maps.google.adapter.CameraUpdateAdapter;
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
	public CameraUpdate newLatLngZoom(LatLng latLng, float zoomLevel) {
		com.google.android.gms.maps.model.LatLng googleLatLng = AnyMapAdapter.adapt(latLng);

		return new CameraUpdateAdapter(
				com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(
						googleLatLng,
						zoomLevel
				)
		);
	}

	@Override
	public CameraUpdate newLatLngBounds(LatLngBounds bounds, int padding) {
		com.google.android.gms.maps.model.LatLngBounds googleBounds = AnyMapAdapter.adapt(bounds);

		return new CameraUpdateAdapter(
				com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds(
						googleBounds,
						padding
				)
		);
	}

	@Override
	public CameraUpdate zoomTo(float zoomLevel) {
		return new CameraUpdateAdapter(
				com.google.android.gms.maps.CameraUpdateFactory.zoomTo(zoomLevel)
		);
	}

}
