/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import net.doo.maps.model.LatLng;
import net.doo.maps.model.LatLngBounds;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.osmdroid.api.IMapController;

import static net.doo.maps.util.OsmUtils.toGeoPoint;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CameraUpdateHandlerTest {

	@Mock
	org.osmdroid.views.MapView map;
	@Mock
	IMapController mapController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		doReturn(mapController)
				.when(map)
				.getController();
	}

	@Test
	public void testMoveCameraToPosition() throws Exception {
		// Given
		CameraUpdateHandler handler = new CameraUpdateHandler(map);

		final LatLng center = new LatLng(10, 10);

		OsmCameraUpdate cameraUpdate = new OsmCameraUpdate.Builder()
				.center(center)
				.build();

		// When
		handler.moveCamera(cameraUpdate);

		// Then
		verify(mapController).setCenter(eq(toGeoPoint(center)));
		verifyNoMoreInteractions(mapController);
	}

	@Test
	public void testMoveCameraToPositionWithZoom() throws Exception {
		// Given
		CameraUpdateHandler handler = new CameraUpdateHandler(map);

		final LatLng center = new LatLng(10, 10);
		final int zoomLevel = 10;

		OsmCameraUpdate cameraUpdate = new OsmCameraUpdate.Builder()
				.center(center)
				.zoom((float) zoomLevel)
				.build();

		// When
		handler.moveCamera(cameraUpdate);

		// Then
		verify(mapController).setCenter(eq(toGeoPoint(center)));
		verify(mapController).setZoom(zoomLevel);
		verifyNoMoreInteractions(mapController);
	}

	@Test
	public void testMoveCameraToBounds() throws Exception {
		// Given
		CameraUpdateHandler handler = new CameraUpdateHandler(map);

		final LatLngBounds bounds = new LatLngBounds(
				new LatLng(10, 10),
				new LatLng(20, 20)
		);

		OsmCameraUpdate cameraUpdate = new OsmCameraUpdate.Builder()
				.bounds(bounds)
				.build();

		// When
		handler.moveCamera(cameraUpdate);

		// Then
		verify(mapController).setCenter(eq(
				toGeoPoint(bounds.getCenter())
		));
		verify(mapController).zoomToSpan(
				eq((int) (10 * 1e6)),
				eq((int) (10 * 1e6))
		);
		verifyNoMoreInteractions(mapController);
	}

	@Test
	public void testAnimateCamera() throws Exception {
		// Given
		CameraUpdateHandler handler = spy(new CameraUpdateHandler(map));

		final LatLng center = new LatLng(10, 10);

		OsmCameraUpdate cameraUpdate = new OsmCameraUpdate.Builder()
				.center(center)
				.build();

		// When
		handler.animateCamera(cameraUpdate);

		// Then
		verify(handler).moveCamera(refEq(cameraUpdate));
	}

	@Test
	public void testAnimateCameraWithCallback() throws Exception {
		// Given
		CameraUpdateHandler handler = spy(new CameraUpdateHandler(map));

		final LatLng center = new LatLng(10, 10);

		OsmCameraUpdate cameraUpdate = new OsmCameraUpdate.Builder()
				.center(center)
				.build();

		AnyMap.CancelableCallback callback = mock(AnyMap.CancelableCallback.class);

		// When
		handler.animateCamera(cameraUpdate, callback);

		// Then
		verify(handler).moveCamera(refEq(cameraUpdate));

		verify(callback).onFinish();
		verifyNoMoreInteractions(callback);
	}

}