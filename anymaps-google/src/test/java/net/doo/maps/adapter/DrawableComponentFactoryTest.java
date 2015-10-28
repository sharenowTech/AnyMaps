/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import net.doo.maps.model.CircleOptions;
import net.doo.maps.model.MarkerOptions;
import net.doo.maps.model.PolygonOptions;
import net.doo.maps.model.PolylineOptions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({GoogleMap.class, Marker.class, AnyMapAdapter.class})
public class DrawableComponentFactoryTest {

	@Before
	public void setUp() throws Exception {
		mockStatic(AnyMapAdapter.class);

		// We are not depending on how AnyMapAdapter works
		when(AnyMapAdapter.adapt(any())).thenReturn(null);
	}

	@Test
	public void testAddMarker() throws Exception {
		// Given
		MarkerOptions options = new MarkerOptions();

		GoogleMap map = mock(GoogleMap.class);

		DrawableComponentFactory factory = new DrawableComponentFactory(map);

		// When
		factory.addMarker(options);

		// Then
		verify(map).addMarker(any(com.google.android.gms.maps.model.MarkerOptions.class));
	}

	@Test
	public void testAddCircle() throws Exception {
		// Given
		CircleOptions options = new CircleOptions();

		GoogleMap map = mock(GoogleMap.class);

		DrawableComponentFactory factory = new DrawableComponentFactory(map);

		// When
		factory.addCircle(options);

		// Then
		verify(map).addCircle(any(com.google.android.gms.maps.model.CircleOptions.class));
	}

	@Test
	public void testAddPolygon() throws Exception {
		// Given
		PolygonOptions options = new PolygonOptions();

		GoogleMap map = mock(GoogleMap.class);

		DrawableComponentFactory factory = new DrawableComponentFactory(map);

		// When
		factory.addPolygon(options);

		// Then
		verify(map).addPolygon(any(com.google.android.gms.maps.model.PolygonOptions.class));
	}

	@Test
	public void testAddPolyline() throws Exception {
		// Given
		PolylineOptions options = new PolylineOptions();

		GoogleMap map = mock(GoogleMap.class);

		DrawableComponentFactory factory = new DrawableComponentFactory(map);

		// When
		factory.addPolyline(options);

		// Then
		verify(map).addPolyline(any(com.google.android.gms.maps.model.PolylineOptions.class));
	}

}