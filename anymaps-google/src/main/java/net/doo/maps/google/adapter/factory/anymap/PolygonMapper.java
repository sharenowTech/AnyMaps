/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter.factory.anymap;

import com.google.android.gms.maps.model.Polygon;

import net.doo.maps.google.adapter.PolygonAdapter;
import net.doo.maps.google.adapter.factory.Mapper;

/**
 * Maps Google Polygon to AnyMap Polygon
 */
public class PolygonMapper implements Mapper<Polygon, net.doo.maps.model.Polygon> {

	@Override
	public net.doo.maps.model.Polygon map(Polygon input) {
		return new PolygonAdapter(input);
	}

}
