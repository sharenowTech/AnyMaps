/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter.factory.google;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import net.doo.maps.adapter.BitmapDescriptorAdapter;
import net.doo.maps.adapter.TestUtil;
import net.doo.maps.model.LatLng;
import net.doo.maps.model.MarkerOptions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BitmapDescriptorFactory.class, BitmapDescriptor.class, Bitmap.class})
public class MarkerOptionsMapperTest {

	@Before
	public void setUp() throws Exception {
		mockStatic(BitmapDescriptorFactory.class);
	}

	@Test
	public void testMap() throws Exception {
		// Given
		BitmapDescriptor bitmapDescriptor = mock(BitmapDescriptor.class);
		BitmapDescriptorAdapter descriptorAdapter = new BitmapDescriptorAdapter(bitmapDescriptor);

		MarkerOptionsMapper mapper = new MarkerOptionsMapper();

		MarkerOptions input = new MarkerOptions()
				.alpha(0.5f)
				.anchor(0.5f, 0.5f)
				.icon(descriptorAdapter)
				.position(new LatLng(10, 10))
				.visible(true);

		// When
		com.google.android.gms.maps.model.MarkerOptions output = mapper.map(input);

		// Then
		assertEquals(
				input.getAlpha(),
				output.getAlpha(),
				0
		);

		assertEquals(
				input.getAnchorU(),
				output.getAnchorU(),
				0
		);

		assertEquals(
				input.getAnchorV(),
				output.getAnchorV(),
				0
		);

		assertEquals(
				bitmapDescriptor,
				output.getIcon()
		);

		TestUtil.assertEquals(
				input.getPosition(),
				output.getPosition()
		);

		assertEquals(
				input.isVisible(),
				output.isVisible()
		);
	}
}