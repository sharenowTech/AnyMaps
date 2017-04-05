/*
 * Copyright (c) 2017 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.baidu;

import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.car2go.maps.AnyMap;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.EnumSet.of;

/**
 * Initializer for Baidu maps.
 */
public class MapsConfiguration implements com.car2go.maps.MapsConfiguration {

	private static final MapsConfiguration instance = new MapsConfiguration();

	private MapsConfiguration() {}

	public static MapsConfiguration getInstance() {
		return instance;
	}

	@Override
	public void initialize(Context context) {
		SDKInitializer.initialize(context);
	}

	@Override
	public Set<AnyMap.Feature> getSupportedFeatures() {
		return unmodifiableSet(of(AnyMap.Feature.MAP_TYPES, AnyMap.Feature.TRAFFIC_LAYER));
	}

}
