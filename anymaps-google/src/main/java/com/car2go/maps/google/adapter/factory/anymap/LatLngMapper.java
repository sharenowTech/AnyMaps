/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.anymap;

import com.car2go.maps.google.adapter.factory.Mapper;
import com.google.android.gms.maps.model.LatLng;

/**
 * Maps Google LatLng to AnyMap LatLng
 */
public class LatLngMapper implements Mapper<LatLng, com.car2go.maps.model.LatLng> {

	@Override
	public com.car2go.maps.model.LatLng map(LatLng input) {
		return new com.car2go.maps.model.LatLng(
				input.latitude,
				input.longitude
		);
	}

}
