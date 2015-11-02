/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import android.content.Context;

import java.util.Set;

// TODO Rename it
public interface MapsUtil {
	/**
	 * Initializes the maps.
	 *
	 * @param context the context
	 */
	void initialize(Context context);

	/**
	 * Provides capabilities of the {@link AnyMap} implementation. If some features are not supported
	 * and you will try to call them - nothing will happen.
	 *
	 * @return capabilities of the {@link AnyMap} implementation.
	 */
	Set<AnyMap.Capability> getCapabilities();
}
