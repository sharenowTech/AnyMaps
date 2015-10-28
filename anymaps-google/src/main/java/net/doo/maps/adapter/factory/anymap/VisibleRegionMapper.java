/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter.factory.anymap;

import com.google.android.gms.maps.model.VisibleRegion;

import net.doo.maps.adapter.AnyMapAdapter;
import net.doo.maps.adapter.factory.Mapper;
import net.doo.maps.model.LatLngBounds;

/**
 * Maps Google VisibleRegion to AnyMap VisibleRegion
 */
public class VisibleRegionMapper implements Mapper<VisibleRegion, net.doo.maps.model.VisibleRegion> {

	@Override
	public net.doo.maps.model.VisibleRegion map(VisibleRegion input) {
		LatLngBounds bounds = AnyMapAdapter.adapt(input.latLngBounds);

		return new net.doo.maps.model.VisibleRegion(bounds);
	}

}
