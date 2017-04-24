/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.anymap;

import com.car2go.maps.google.adapter.PolygonAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.google.android.gms.maps.model.Polygon;

/**
 * Maps Google Polygon to AnyMap Polygon
 */
public class PolygonMapper implements Mapper<Polygon, com.car2go.maps.model.Polygon> {

	@Override
	public com.car2go.maps.model.Polygon map(Polygon input) {
		return new PolygonAdapter(input);
	}

}
