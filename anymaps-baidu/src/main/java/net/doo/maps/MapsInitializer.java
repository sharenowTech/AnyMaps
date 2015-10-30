/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import android.content.Context;

import com.baidu.mapapi.SDKInitializer;

/**
 * Utility functions to ensure that map is initialized
 */
public class MapsInitializer {

	/**
	 * Ensures that map is initialized
	 */
	public static void initialize(Context context) {
		SDKInitializer.initialize(context);
	}

}
