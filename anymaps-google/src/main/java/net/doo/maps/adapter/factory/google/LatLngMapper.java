/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter.factory.google;

import net.doo.maps.adapter.factory.Mapper;
import net.doo.maps.model.LatLng;

/**
 * Maps AnyMap LatLng to Google LatLng
 */
public class LatLngMapper implements Mapper<LatLng, com.google.android.gms.maps.model.LatLng> {

	@Override
	public com.google.android.gms.maps.model.LatLng map(LatLng input) {
		return new com.google.android.gms.maps.model.LatLng(
				input.latitude,
				input.longitude
		);
	}

}
