/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.osm.drawable;

import android.graphics.drawable.BitmapDrawable;

import net.doo.maps.osm.OsmBitmapDescriptor;
import net.doo.maps.osm.drawable.overlay.MarkerOverlayItem;
import net.doo.maps.model.BitmapDescriptor;
import net.doo.maps.model.LatLng;
import net.doo.maps.model.Marker;
import net.doo.maps.model.MarkerOptions;
import net.doo.maps.osm.util.OsmUtils;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;

import static net.doo.maps.osm.util.OsmUtils.anchorToHotspot;

/**
 * Draws marker on OpenStreetMaps.
 * Associated with given {@link net.doo.maps.AnyMap} object, so it should not be cached.
 */
public class OsmMarker implements Marker {

	private final MapView map;

	private final LatLng position;
	private final ItemizedIconOverlay<MarkerOverlayItem> overlay;
	private final MarkerOverlayItem overlayItem;

	private boolean removed = false;
	private boolean visible = true;

	OsmMarker(MapView map, MarkerOptions options, ItemizedIconOverlay<MarkerOverlayItem> markersOverlay) {
		this.map = map;

		overlayItem = new MarkerOverlayItem(
				OsmUtils.toGeoPoint(options.getPosition()),
				this
		);

		OsmBitmapDescriptor descriptor = (OsmBitmapDescriptor) options.getIcon();
		overlayItem.setMarker(
				new BitmapDrawable(map.getResources(), descriptor.bitmap)
		);
		overlayItem.setMarkerHotspot(
				anchorToHotspot(options.getAnchorU(), options.getAnchorV())
		);

		markersOverlay.addItem(overlayItem);

		overlay = markersOverlay;
		position = options.getPosition();

		map.invalidate();
	}

	@Override
	public void setIcon(BitmapDescriptor icon) {
		OsmBitmapDescriptor descriptor = (OsmBitmapDescriptor) icon;

		overlayItem.setMarker(new BitmapDrawable(map.getResources(), descriptor.bitmap));
		map.invalidate();
	}

	@Override
	public LatLng getPosition() {
		return position;
	}

	@Override
	public void showInfoWindow() {
		// Do nothing
	}

	@Override
	public void setVisible(boolean visible) {
		if (removed || this.visible == visible) {
			return;
		}

		if (visible) {
			overlay.addItem(overlayItem);
		} else {
			overlay.removeItem(overlayItem);
		}

		this.visible = visible;

		map.invalidate();
	}

	@Override
	public void remove() {
		if (!visible) {
			removed = true;
		}

		if (removed) {
			return;
		}

		overlay.removeItem(overlayItem);
		removed = true;
		visible = false;

		map.invalidate();
	}
}
