/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.anymap;

import com.car2go.maps.google.adapter.AnyMapAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.car2go.maps.model.LatLng;
import com.google.android.gms.maps.model.CameraPosition;

/**
 * Maps Google CameraPosition to AnyMap CameraPosition
 */
public class CameraPositionMapper implements Mapper<CameraPosition, com.car2go.maps.model.CameraPosition> {

	@Override
	public com.car2go.maps.model.CameraPosition map(CameraPosition input) {
		LatLng anyLatLng = AnyMapAdapter.adapt(input.target);

		return new com.car2go.maps.model.CameraPosition(
				anyLatLng,
				input.zoom
		);
	}

}
