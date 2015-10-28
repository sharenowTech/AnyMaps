/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import android.content.Context;

/**
 * Utility functions to ensure that map is initialized
 */
public class MapsInitializer {

	/**
	 * Ensures that map is initialized
	 */
	public static void initialize(Context context) {
		com.google.android.gms.maps.MapsInitializer.initialize(context);
	}

}
