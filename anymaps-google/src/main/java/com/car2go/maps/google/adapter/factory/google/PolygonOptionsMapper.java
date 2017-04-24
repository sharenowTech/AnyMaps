/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.google;

import com.car2go.maps.google.adapter.AnyMapAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.car2go.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Maps AnyMap PolygonOptions to Google PolygonOptions
 */
public class PolygonOptionsMapper implements Mapper<PolygonOptions, com.google.android.gms.maps.model.PolygonOptions> {

	@Override
	public com.google.android.gms.maps.model.PolygonOptions map(PolygonOptions input) {
		List<LatLng> points = AnyMapAdapter.adaptList(com.car2go.maps.model.LatLng.class, input.getPoints());

		return new com.google.android.gms.maps.model.PolygonOptions()
				.fillColor(input.getFillColor())
				.strokeColor(input.getStrokeColor())
				.strokeWidth(input.getStrokeWidth())
				.addAll(points);
	}

}
