/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter.factory.anymap;

import com.google.android.gms.maps.model.LatLng;

import net.doo.maps.google.adapter.TestUtil;

import org.junit.Test;

public class LatLngMapperTest {

	@Test
	public void testMap() throws Exception {
		// Given
		LatLngMapper mapper = new LatLngMapper();

		LatLng input = new LatLng(10, 10);

		// When
		net.doo.maps.model.LatLng output = mapper.map(input);

		// Then
		TestUtil.assertEquals(
				input,
				output
		);
	}
}