/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter;

import com.google.android.gms.maps.model.Polyline;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Polyline.class)
public class PolylineAdapterTest {

	@Test
	public void testDelegateMethods() throws Exception {
		// Given
		Polyline polyline = mock(Polyline.class);

		PolylineAdapter adapter = new PolylineAdapter(polyline);

		// When
		adapter.setVisible(true);
		adapter.remove();

		// Then
		verify(polyline).setVisible(eq(true));
		verify(polyline).remove();
	}

}