/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.anymap;

import com.car2go.maps.google.adapter.TestUtil;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.junit.Test;

public class LatLngBoundsMapperTest {

	@Test
	public void testMap() throws Exception {
		// Given
		LatLngBoundsMapper mapper = new LatLngBoundsMapper();

		LatLngBounds input = new LatLngBounds(
				new LatLng(10, 10),
				new LatLng(20, 20)
		);

		// When
		com.car2go.maps.model.LatLngBounds output = mapper.map(input);

		// Then
		TestUtil.assertEquals(
				input.southwest,
				output.southwest
		);

		TestUtil.assertEquals(
				input.northeast,
				output.northeast
		);
	}

}