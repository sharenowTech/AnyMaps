/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.anymap;

import com.car2go.maps.google.adapter.AnyMapAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.car2go.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.VisibleRegion;

/**
 * Maps Google VisibleRegion to AnyMap VisibleRegion
 */
public class VisibleRegionMapper implements Mapper<VisibleRegion, com.car2go.maps.model.VisibleRegion> {

	@Override
	public com.car2go.maps.model.VisibleRegion map(VisibleRegion input) {
		LatLngBounds bounds = AnyMapAdapter.adapt(input.latLngBounds);

		return new com.car2go.maps.model.VisibleRegion(bounds);
	}

}
