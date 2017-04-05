/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter;

import com.google.android.gms.maps.UiSettings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UiSettings.class)
public class UiSettingsAdapterTest {

	@Test
	public void testDelegateMethods() throws Exception {
		// Given
		UiSettings uiSettings = mock(UiSettings.class);

		UiSettingsAdapter adapter = new UiSettingsAdapter(uiSettings);

		// When
		adapter.setAllGesturesEnabled(true);
		adapter.setMapToolbarEnabled(true);
		adapter.setMyLocationButtonEnabled(true);

		// Then
		verify(uiSettings).setAllGesturesEnabled(eq(true));
		verify(uiSettings).setMapToolbarEnabled(eq(true));
		verify(uiSettings).setMyLocationButtonEnabled(true);
	}

}