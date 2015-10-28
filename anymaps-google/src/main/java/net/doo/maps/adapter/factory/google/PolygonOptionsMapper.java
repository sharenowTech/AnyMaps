/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter.factory.google;

import com.google.android.gms.maps.model.LatLng;

import net.doo.maps.adapter.AnyMapAdapter;
import net.doo.maps.adapter.factory.Mapper;
import net.doo.maps.model.PolygonOptions;

import java.util.List;

/**
 * Maps AnyMap PolygonOptions to Google PolygonOptions
 */
public class PolygonOptionsMapper implements Mapper<PolygonOptions, com.google.android.gms.maps.model.PolygonOptions> {

	@Override
	public com.google.android.gms.maps.model.PolygonOptions map(PolygonOptions input) {
		List<LatLng> points = AnyMapAdapter.adaptList(net.doo.maps.model.LatLng.class, input.getPoints());

		return new com.google.android.gms.maps.model.PolygonOptions()
				.fillColor(input.getFillColor())
				.strokeColor(input.getStrokeColor())
				.strokeWidth(input.getStrokeWidth())
				.addAll(points);
	}

}
