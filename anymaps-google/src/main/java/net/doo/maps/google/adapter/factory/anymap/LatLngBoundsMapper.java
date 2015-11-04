/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter.factory.anymap;

import com.google.android.gms.maps.model.LatLngBounds;

import net.doo.maps.google.adapter.AnyMapAdapter;
import net.doo.maps.google.adapter.factory.Mapper;
import net.doo.maps.model.LatLng;

/**
 * Maps Google LatLngBounds to AnyMap LatLngBounds
 */
public class LatLngBoundsMapper implements Mapper<LatLngBounds, net.doo.maps.model.LatLngBounds> {

	@Override
	public net.doo.maps.model.LatLngBounds map(LatLngBounds input) {
		LatLng southWest = AnyMapAdapter.adapt(input.southwest);
		LatLng northEast = AnyMapAdapter.adapt(input.northeast);

		return new net.doo.maps.model.LatLngBounds(southWest, northEast);
	}

}
