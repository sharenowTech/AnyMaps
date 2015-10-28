/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import net.doo.maps.adapter.AnyMapAdapter;
import net.doo.maps.adapter.CameraUpdateAdapter;
import net.doo.maps.model.LatLng;
import net.doo.maps.model.LatLngBounds;

/**
 * Creates {@link CameraUpdate} objects which can be used to update map camera
 */
public class CameraUpdateFactory {

	/**
	 * @return {@link CameraUpdate} which moves camera to given position with given zoom level.
	 */
	public static CameraUpdate newLatLngZoom(LatLng latLng, float zoomLevel) {
		com.google.android.gms.maps.model.LatLng googleLatLng = AnyMapAdapter.adapt(latLng);

		return new CameraUpdateAdapter(
				com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(
						googleLatLng,
						zoomLevel
				)
		);
	}

	/**
	 * @return {@link CameraUpdate} which moves camera so it displays given bounds with given padding.
	 */
	public static CameraUpdate newLatLngBounds(LatLngBounds bounds, int padding) {
		com.google.android.gms.maps.model.LatLngBounds googleBounds = AnyMapAdapter.adapt(bounds);

		return new CameraUpdateAdapter(
				com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds(
						googleBounds,
						padding
				)
		);
	}

	/**
	 * @return {@link CameraUpdate} which zooms camera to given zoom level.
	 */
	public static CameraUpdate zoomTo(float zoomLevel) {
		return new CameraUpdateAdapter(
				com.google.android.gms.maps.CameraUpdateFactory.zoomTo(zoomLevel)
		);
	}

}
