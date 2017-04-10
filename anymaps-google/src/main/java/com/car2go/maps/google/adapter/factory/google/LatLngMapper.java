/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.google;

import com.car2go.maps.google.adapter.factory.Mapper;
import com.car2go.maps.model.LatLng;

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
