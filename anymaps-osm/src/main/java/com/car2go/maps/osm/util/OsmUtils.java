/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.osm.util;

import com.car2go.maps.model.LatLng;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

/**
 * OpenStreetMaps related utilities
 */
public class OsmUtils {

	private static final float ANCHOR_LOW_THRESHOLD = 0.2f;
	private static final float ANCHOR_HIGH_THRESHOLD = 0.7f;

	/**
	 * @return {@link IGeoPoint} with same coordinates as {@link LatLng}
	 */
	public static IGeoPoint toGeoPoint(LatLng latLng) {
		return new GeoPoint(
				latLng.latitude,
				latLng.longitude
		);
	}

	/**
	 * Convenience for {@link #toGeoPoint(LatLng)} which works on {@link List}
	 */
	public static List<IGeoPoint> toGeoPoints(List<LatLng> points) {
		List<IGeoPoint> result = new ArrayList<>();

		for (LatLng point : points) {
			result.add(
					toGeoPoint(point)
			);
		}

		return result;
	}

	/**
	 * @return {@link LatLng} with same coordinates as {@link IGeoPoint}
	 */
	public static LatLng toLatLng(IGeoPoint geoPoint) {
		return new LatLng(
				geoPoint.getLatitude(),
				geoPoint.getLongitude()
		);
	}

	/**
	 * Since OpenStreetMaps does not support UV coordinates for anchors there is a need for
	 * explicit conversion. This method searches for
	 * {@link org.osmdroid.views.overlay.OverlayItem.HotspotPlace} which is closest to given UV
	 * coordinates.
	 *
	 * @return {@link org.osmdroid.views.overlay.OverlayItem.HotspotPlace} which is closest to
	 * given UV coordinates.
	 */
	public static OverlayItem.HotspotPlace anchorToHotspot(float u, float v) {
		if (v > ANCHOR_HIGH_THRESHOLD) {
			if (u < ANCHOR_LOW_THRESHOLD) {
				return OverlayItem.HotspotPlace.LOWER_LEFT_CORNER;
			} else if (u > ANCHOR_HIGH_THRESHOLD) {
				return OverlayItem.HotspotPlace.LOWER_RIGHT_CORNER;
			} else {
				return OverlayItem.HotspotPlace.BOTTOM_CENTER;
			}
		} else if (v < ANCHOR_LOW_THRESHOLD) {
			if (u < ANCHOR_LOW_THRESHOLD) {
				return OverlayItem.HotspotPlace.UPPER_LEFT_CORNER;
			} else if (u > ANCHOR_HIGH_THRESHOLD) {
				return OverlayItem.HotspotPlace.UPPER_RIGHT_CORNER;
			} else {
				return OverlayItem.HotspotPlace.TOP_CENTER;
			}
		} else {
			if (u < ANCHOR_LOW_THRESHOLD) {
				return OverlayItem.HotspotPlace.LEFT_CENTER;
			} else if (u > ANCHOR_HIGH_THRESHOLD) {
				return OverlayItem.HotspotPlace.RIGHT_CENTER;
			} else {
				return OverlayItem.HotspotPlace.CENTER;
			}
		}
	}

}
