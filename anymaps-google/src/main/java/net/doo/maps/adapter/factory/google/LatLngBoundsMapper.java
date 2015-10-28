/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter.factory.google;

import com.google.android.gms.maps.model.LatLng;

import net.doo.maps.adapter.AnyMapAdapter;
import net.doo.maps.adapter.factory.Mapper;
import net.doo.maps.model.LatLngBounds;

/**
 * Maps AnyMap LatLngBounds to Google LatLngBounds
 */
public class LatLngBoundsMapper implements Mapper<LatLngBounds, com.google.android.gms.maps.model.LatLngBounds> {

	@Override
	public com.google.android.gms.maps.model.LatLngBounds map(LatLngBounds input) {
		LatLng southWest = AnyMapAdapter.adapt(input.southwest);
		LatLng northEast = AnyMapAdapter.adapt(input.northeast);

		return new com.google.android.gms.maps.model.LatLngBounds(southWest, northEast);
	}

}
