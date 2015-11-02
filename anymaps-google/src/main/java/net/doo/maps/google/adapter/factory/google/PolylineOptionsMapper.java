/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter.factory.google;

import com.google.android.gms.maps.model.LatLng;

import net.doo.maps.google.adapter.AnyMapAdapter;
import net.doo.maps.google.adapter.factory.Mapper;
import net.doo.maps.model.PolylineOptions;

import java.util.List;

/**
 * Maps AnyMap PolylineOptions to Google PolylineOptions
 */
public class PolylineOptionsMapper implements Mapper<PolylineOptions, com.google.android.gms.maps.model.PolylineOptions> {

	@Override
	public com.google.android.gms.maps.model.PolylineOptions map(PolylineOptions input) {
		List<LatLng> points = AnyMapAdapter.adaptList(net.doo.maps.model.LatLng.class, input.getPoints());

		return new com.google.android.gms.maps.model.PolylineOptions()
				.color(input.getColor())
				.width(input.getWidth())
				.addAll(points);
	}

}
