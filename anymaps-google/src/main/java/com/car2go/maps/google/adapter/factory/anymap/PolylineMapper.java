/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.anymap;

import com.car2go.maps.google.adapter.PolylineAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.google.android.gms.maps.model.Polyline;

/**
 * Maps Google Polyline to AnyMap Polyline
 */
public class PolylineMapper implements Mapper<Polyline, com.car2go.maps.model.Polyline> {

	@Override
	public com.car2go.maps.model.Polyline map(Polyline input) {
		return new PolylineAdapter(input);
	}

}
