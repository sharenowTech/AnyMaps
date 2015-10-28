/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter.factory.anymap;

import com.google.android.gms.maps.model.LatLng;

import net.doo.maps.adapter.factory.Mapper;

/**
 * Maps Google LatLng to AnyMap LatLng
 */
public class LatLngMapper implements Mapper<LatLng, net.doo.maps.model.LatLng> {

	@Override
	public net.doo.maps.model.LatLng map(LatLng input) {
		return new net.doo.maps.model.LatLng(
				input.latitude,
				input.longitude
		);
	}

}
