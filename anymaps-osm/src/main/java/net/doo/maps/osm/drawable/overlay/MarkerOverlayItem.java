/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.osm.drawable.overlay;

import net.doo.maps.model.Marker;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.overlay.OverlayItem;

/**
 * Overlay item for {@link org.osmdroid.views.overlay.ItemizedIconOverlay} which is associated
 * with {@link Marker}
 */
public class MarkerOverlayItem extends OverlayItem {

	public final Marker marker;

	/**
	 * @param geoPoint position of the item
	 * @param marker   associated {@link Marker}
	 */
	public MarkerOverlayItem(IGeoPoint geoPoint, Marker marker) {
		super(null, null, geoPoint);

		this.marker = marker;
	}

}
