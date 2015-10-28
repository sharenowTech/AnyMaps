/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter.factory.google;

import net.doo.maps.adapter.TestUtil;
import net.doo.maps.model.LatLng;
import net.doo.maps.model.PolylineOptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PolylineOptionsMapperTest {

	@Test
	public void testMap() throws Exception {
		// Given
		PolylineOptionsMapper mapper = new PolylineOptionsMapper();

		PolylineOptions input = new PolylineOptions()
				.color(0xff0000)
				.width(10f)
				.add(new LatLng(10, 10));

		// When
		com.google.android.gms.maps.model.PolylineOptions output = mapper.map(input);

		// Then
		assertEquals(
				input.getColor(),
				output.getColor()
		);

		assertEquals(
				input.getWidth(),
				output.getWidth(),
				0
		);

		assertEquals(
				1,
				output.getPoints().size()
		);

		TestUtil.assertEquals(
				input.getPoints().get(0),
				output.getPoints().get(0)
		);
	}

}