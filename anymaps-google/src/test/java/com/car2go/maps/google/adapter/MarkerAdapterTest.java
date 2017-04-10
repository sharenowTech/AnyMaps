/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter;


import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.Marker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Marker.class, BitmapDescriptor.class})
public class MarkerAdapterTest {

	@Test
	public void testDelegateMethods() throws Exception {
		// Given
		Marker marker = mock(Marker.class);

		BitmapDescriptor descriptor = mock(BitmapDescriptor.class);
		BitmapDescriptorAdapter adapter = new BitmapDescriptorAdapter(descriptor);

		MarkerAdapter markerAdapter = new MarkerAdapter(marker);

		// When
		markerAdapter.setVisible(true);
		markerAdapter.setIcon(adapter);
		markerAdapter.getPosition();
		markerAdapter.showInfoWindow();
		markerAdapter.remove();

		// Then
		verify(marker).setVisible(eq(true));
		verify(marker).setIcon(refEq(descriptor));
		verify(marker).getPosition();
		verify(marker).showInfoWindow();
		verify(marker).remove();
	}

}