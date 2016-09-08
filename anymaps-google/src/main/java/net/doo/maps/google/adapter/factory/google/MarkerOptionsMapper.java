/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter.factory.google;

import com.google.android.gms.maps.model.LatLng;

import net.doo.maps.google.adapter.AnyMapAdapter;
import net.doo.maps.google.adapter.BitmapDescriptorAdapter;
import net.doo.maps.google.adapter.factory.Mapper;
import net.doo.maps.model.MarkerOptions;

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
