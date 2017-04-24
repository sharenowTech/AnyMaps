/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.google;

import com.car2go.maps.google.adapter.AnyMapAdapter;
import com.car2go.maps.google.adapter.BitmapDescriptorAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.car2go.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;

/**
 * Maps AnyMap MarkerOptions to Google MarkerOptions
 */
public class MarkerOptionsMapper implements Mapper<MarkerOptions, com.google.android.gms.maps.model.MarkerOptions> {

	@Override
	public com.google.android.gms.maps.model.MarkerOptions map(MarkerOptions input) {
		LatLng googleLatLng = AnyMapAdapter.adapt(input.getPosition());

		return new com.google.android.gms.maps.model.MarkerOptions()
				.alpha(input.getAlpha())
				.position(googleLatLng)
				.visible(input.isVisible())
				.anchor(
						input.getAnchorU(),
						input.getAnchorV()
				)
				.icon(
						((BitmapDescriptorAdapter) input.getIcon()).wrappedDescriptor
				)
				.zIndex(input.getZ());
	}

}
