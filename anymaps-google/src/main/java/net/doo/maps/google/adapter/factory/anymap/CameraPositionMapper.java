/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter.factory.anymap;

import com.google.android.gms.maps.model.CameraPosition;

import net.doo.maps.google.adapter.AnyMapAdapter;
import net.doo.maps.google.adapter.factory.Mapper;
import net.doo.maps.model.LatLng;

/**
 * Maps Google CameraPosition to AnyMap CameraPosition
 */
public class CameraPositionMapper implements Mapper<CameraPosition, net.doo.maps.model.CameraPosition> {

	@Override
	public net.doo.maps.model.CameraPosition map(CameraPosition input) {
		LatLng anyLatLng = AnyMapAdapter.adapt(input.target);

		return new net.doo.maps.model.CameraPosition(
				anyLatLng,
				input.zoom
		);
	}

}
