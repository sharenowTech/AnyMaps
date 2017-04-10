/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter;

import com.car2go.maps.model.LatLng;

import org.junit.Assert;

public class TestUtil {

	public static void assertEquals(LatLng expected,
	                                com.google.android.gms.maps.model.LatLng actual) {
		Assert.assertEquals(
				expected.latitude,
				actual.latitude,
				0
		);

		Assert.assertEquals(
				expected.longitude,
				actual.longitude,
				0
		);
	}

	public static void assertEquals(com.google.android.gms.maps.model.LatLng expected,
	                                LatLng actual) {
		Assert.assertEquals(
				expected.latitude,
				actual.latitude,
				0
		);

		Assert.assertEquals(
				expected.longitude,
				actual.longitude,
				0
		);
	}

}
