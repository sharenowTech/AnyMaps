/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.google;

import com.car2go.maps.google.adapter.AnyMapAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.car2go.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

/**
 * Maps AnyMap CircleOptions to Google CircleOptions
 */
public class CircleOptionsMapper implements Mapper<CircleOptions, com.google.android.gms.maps.model.CircleOptions> {

	@Override
	public com.google.android.gms.maps.model.CircleOptions map(CircleOptions input) {
		LatLng center = AnyMapAdapter.adapt(input.getCenter());

		return new com.google.android.gms.maps.model.CircleOptions()
				.center(center)
				.radius(input.getRadius())
				.fillColor(input.getFillColor())
				.strokeColor(input.getStrokeColor())
				.strokeWidth(input.getStrokeWidth());
	}

}
