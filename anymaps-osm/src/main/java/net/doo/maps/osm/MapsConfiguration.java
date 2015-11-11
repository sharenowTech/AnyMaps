/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.osm;

import android.content.Context;

import net.doo.maps.AnyMap;

import java.util.Collections;
import java.util.Set;

/**
 * Initializer for OSM maps.
 */
public final class MapsConfiguration implements net.doo.maps.MapsConfiguration {

	private static final MapsConfiguration instance = new MapsConfiguration();

	private MapsConfiguration() {
	}

	public static MapsConfiguration getInstance() {
		return instance;
	}

	@Override
	public void initialize(Context context) {
		BitmapDescriptorFactory.initialize(context);
	}

	@Override
	public Set<AnyMap.Feature> getSupportedFeatures() {
		return Collections.singleton(AnyMap.Feature.REVEALABLE);
	}

}
