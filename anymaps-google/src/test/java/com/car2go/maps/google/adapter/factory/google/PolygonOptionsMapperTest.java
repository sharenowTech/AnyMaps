/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.google;

import com.car2go.maps.google.adapter.TestUtil;
import com.car2go.maps.model.LatLng;
import com.car2go.maps.model.PolygonOptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PolygonOptionsMapperTest {

	@Test
	public void testMap() throws Exception {
		// Given
		PolygonOptionsMapper mapper = new PolygonOptionsMapper();

		PolygonOptions input = new PolygonOptions()
				.strokeColor(0xff0000)
				.fillColor(0x00ff00)
				.strokeWidth(10f)
				.add(new LatLng(10, 10));

		// When
		com.google.android.gms.maps.model.PolygonOptions output = mapper.map(input);

		// Then
		assertEquals(
				input.getStrokeColor(),
				output.getStrokeColor()
		);

		assertEquals(
				input.getFillColor(),
				output.getFillColor()
		);

		assertEquals(
				input.getStrokeWidth(),
				output.getStrokeWidth(),
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