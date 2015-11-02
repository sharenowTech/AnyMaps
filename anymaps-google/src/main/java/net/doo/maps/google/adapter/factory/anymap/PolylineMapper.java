/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter.factory.anymap;

import com.google.android.gms.maps.model.Polyline;

import net.doo.maps.google.adapter.PolylineAdapter;
import net.doo.maps.google.adapter.factory.Mapper;

/**
 * Maps Google Polyline to AnyMap Polyline
 */
public class PolylineMapper implements Mapper<Polyline, net.doo.maps.model.Polyline> {

	@Override
	public net.doo.maps.model.Polyline map(Polyline input) {
		return new PolylineAdapter(input);
	}

}
