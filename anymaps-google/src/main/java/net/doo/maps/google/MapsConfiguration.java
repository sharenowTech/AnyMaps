/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google;

import android.content.Context;

import net.doo.maps.AnyMap;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.EnumSet.of;

/**
 * Initializer for Google maps.
 */
public class MapsConfiguration implements net.doo.maps.MapsConfiguration {

	@Override
	public void initialize(Context context) {
		com.google.android.gms.maps.MapsInitializer.initialize(context);
	}

	@Override
	public Set<AnyMap.Configuration> getConfigurations() {
		return unmodifiableSet(of(AnyMap.Configuration.MAP_TYPES, AnyMap.Configuration.TRAFFIC_LAYER, AnyMap.Configuration.REVEALABLE));
	}


}
