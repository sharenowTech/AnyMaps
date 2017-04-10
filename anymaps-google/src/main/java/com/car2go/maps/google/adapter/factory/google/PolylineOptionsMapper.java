/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.google;

import com.car2go.maps.google.adapter.AnyMapAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.car2go.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Maps AnyMap PolylineOptions to Google PolylineOptions
 */
public class PolylineOptionsMapper implements Mapper<PolylineOptions, com.google.android.gms.maps.model.PolylineOptions> {

	@Override
	public com.google.android.gms.maps.model.PolylineOptions map(PolylineOptions input) {
		List<LatLng> points = AnyMapAdapter.adaptList(com.car2go.maps.model.LatLng.class, input.getPoints());

		return new com.google.android.gms.maps.model.PolylineOptions()
				.color(input.getColor())
				.width(input.getWidth())
				.addAll(points);
	}

}
