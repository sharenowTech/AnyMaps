/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.osm.util;

import net.doo.maps.model.LatLng;

import org.junit.Test;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class OsmUtilsTest {

	@Test
	public void testToGeoPoint() throws Exception {
		// Given
		final LatLng input = new LatLng(10d, 20d);

		final GeoPoint expected = new GeoPoint(10d, 20d);

		// When
		IGeoPoint output = OsmUtils.toGeoPoint(input);

		// Then
		assertEquals(expected, output);
	}

	@Test
	public void testToGeoPoints() throws Exception {
		// Given
		final List<LatLng> input = asList(
				new LatLng(10d, 20d),
				new LatLng(30d, 40d)
		);

		final List<GeoPoint> expected = asList(
				new GeoPoint(10d, 20d),
				new GeoPoint(30d, 40d)
		);

		// When
		List<IGeoPoint> output = OsmUtils.toGeoPoints(input);

		// Then
		assertEquals(expected, output);
	}

	@Test
	public void testToLatLng() throws Exception {
		// Given
		final GeoPoint input = new GeoPoint(10d, 20d);

		final LatLng expected = new LatLng(10d, 20d);

		// When
		LatLng output = OsmUtils.toLatLng(input);

		// Then
		assertEquals(expected, output);
	}

	@Test
	public void testAnchorToHotspot() throws Exception {
		assertEquals(
				OverlayItem.HotspotPlace.UPPER_LEFT_CORNER,
				OsmUtils.anchorToHotspot(0f, 0f)
		);

		assertEquals(
				OverlayItem.HotspotPlace.TOP_CENTER,
				OsmUtils.anchorToHotspot(0.5f, 0f)
		);

		assertEquals(
				OverlayItem.HotspotPlace.UPPER_RIGHT_CORNER,
				OsmUtils.anchorToHotspot(1f, 0f)
		);

		assertEquals(
				OverlayItem.HotspotPlace.LEFT_CENTER,
				OsmUtils.anchorToHotspot(0f, 0.5f)
		);

		assertEquals(
				OverlayItem.HotspotPlace.CENTER,
				OsmUtils.anchorToHotspot(0.5f, 0.5f)
		);

		assertEquals(
				OverlayItem.HotspotPlace.RIGHT_CENTER,
				OsmUtils.anchorToHotspot(1f, 0.5f)
		);

		assertEquals(
				OverlayItem.HotspotPlace.LOWER_LEFT_CORNER,
				OsmUtils.anchorToHotspot(0f, 1f)
		);

		assertEquals(
				OverlayItem.HotspotPlace.BOTTOM_CENTER,
				OsmUtils.anchorToHotspot(0.5f, 1f)
		);
		assertEquals(
				OverlayItem.HotspotPlace.LOWER_RIGHT_CORNER,
				OsmUtils.anchorToHotspot(1f, 1f)
		);

	}

}