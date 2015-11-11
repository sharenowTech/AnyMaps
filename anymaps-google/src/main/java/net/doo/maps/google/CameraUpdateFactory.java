/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google;

import net.doo.maps.CameraUpdate;
import net.doo.maps.google.adapter.AnyMapAdapter;
import net.doo.maps.google.adapter.CameraUpdateAdapter;
import net.doo.maps.model.LatLng;
import net.doo.maps.model.LatLngBounds;

/**
 * Creates {@link CameraUpdate} objects which can be used to update map camera
 */
public class CameraUpdateFactory implements net.doo.maps.CameraUpdateFactory {

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
