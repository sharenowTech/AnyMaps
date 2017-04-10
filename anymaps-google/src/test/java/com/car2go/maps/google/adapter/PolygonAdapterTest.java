/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter;

import com.car2go.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.List;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Polygon.class, AnyMapAdapter.class})
public class PolygonAdapterTest {

	@Before
	public void setUp() throws Exception {
		mockStatic(AnyMapAdapter.class);
	}

	@Test
	public void testDelegateMethods() throws Exception {
		// Given
		Polygon polygon = mock(Polygon.class);

		PolygonAdapter polygonAdapter = new PolygonAdapter(polygon);

		// When
		polygonAdapter.setVisible(true);
		polygonAdapter.setHoles(Collections.<List<LatLng>>emptyList());
		polygonAdapter.getPoints();
		polygonAdapter.remove();

		// Then
		verify(polygon).setVisible(eq(true));
		verify(polygon).setHoles(anyList());
		verify(polygon).getPoints();
		verify(polygon).remove();
	}

}