/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter;

import com.google.android.gms.maps.model.Circle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Circle.class)
public class CircleAdapterTest {

	@Test
	public void testDelegateMethods() throws Exception {
		// Given
		Circle circle = mock(Circle.class);

		CircleAdapter circleAdapter = new CircleAdapter(circle);

		// When
		circleAdapter.setVisible(true);
		circleAdapter.remove();

		// Then
		verify(circle).setVisible(eq(true));
		verify(circle).remove();
	}

}