/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.google;

import com.car2go.maps.google.adapter.AnyMapAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.car2go.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLng;

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
