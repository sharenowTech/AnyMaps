/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.anymap;

import com.car2go.maps.google.adapter.AnyMapAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.car2go.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Maps Google LatLngBounds to AnyMap LatLngBounds
 */
public class LatLngBoundsMapper implements Mapper<LatLngBounds, com.car2go.maps.model.LatLngBounds> {

	@Override
	public com.car2go.maps.model.LatLngBounds map(LatLngBounds input) {
		LatLng southWest = AnyMapAdapter.adapt(input.southwest);
		LatLng northEast = AnyMapAdapter.adapt(input.northeast);

		return new com.car2go.maps.model.LatLngBounds(southWest, northEast);
	}

}
