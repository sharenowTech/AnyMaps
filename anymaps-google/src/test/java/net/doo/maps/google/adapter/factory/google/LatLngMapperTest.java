/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter.factory.google;

import net.doo.maps.google.adapter.TestUtil;
import net.doo.maps.model.LatLng;

import org.junit.Test;

public class LatLngMapperTest {

	@Test
	public void testMap() throws Exception {
		// Given
		LatLngMapper mapper = new LatLngMapper();

		LatLng input = new LatLng(10, 10);

		// When
		com.google.android.gms.maps.model.LatLng output = mapper.map(input);

		// Then
		TestUtil.assertEquals(input, output);
	}

}