/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter.factory.google;

import net.doo.maps.google.adapter.TestUtil;
import net.doo.maps.model.CircleOptions;
import net.doo.maps.model.LatLng;

import org.junit.Test;

import static org.junit.Assert.*;

public class CircleOptionsMapperTest {

	@Test
	public void testMap() throws Exception {
		// Given
		CircleOptionsMapper mapper = new CircleOptionsMapper();

		CircleOptions input = new CircleOptions()
				.center(new LatLng(10, 10))
				.fillColor(0xff0000)
				.radius(100)
				.strokeColor(0x00ff00)
				.strokeWidth(5);

		// When
		com.google.android.gms.maps.model.CircleOptions output = mapper.map(input);

		// Then
		TestUtil.assertEquals(
				input.getCenter(),
				output.getCenter()
		);

		assertEquals(
				input.getFillColor(),
				output.getFillColor()
		);

		assertEquals(
				input.getRadius(),
				output.getRadius(),
				0
		);

		assertEquals(
				input.getStrokeColor(),
				output.getStrokeColor()
		);

		assertEquals(
				input.getStrokeWidth(),
				output.getStrokeWidth(),
				0
		);
	}

}