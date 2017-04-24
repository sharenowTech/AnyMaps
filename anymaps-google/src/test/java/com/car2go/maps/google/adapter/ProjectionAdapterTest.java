/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter;

import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.VisibleRegion;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Projection.class, VisibleRegion.class, AnyMapAdapter.class})
public class ProjectionAdapterTest {

	@Before
	public void setUp() throws Exception {
		mockStatic(AnyMapAdapter.class);
	}

	@Test
	public void testGetVisibleRegion() throws Exception {
		// Given
		Projection projection = mock(Projection.class);
		VisibleRegion visibleRegion = mock(VisibleRegion.class);

		doReturn(visibleRegion)
				.when(projection)
				.getVisibleRegion();

		ProjectionAdapter adapter = new ProjectionAdapter(projection);

		// When
		adapter.getVisibleRegion();

		// Then
		verify(projection).getVisibleRegion();

		verifyStatic();
		AnyMapAdapter.adapt(refEq(visibleRegion));
	}

}