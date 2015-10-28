/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.drawable;

import net.doo.maps.AnyMap;
import net.doo.maps.drawable.overlay.MarkerOverlayItem;
import net.doo.maps.model.Circle;
import net.doo.maps.model.CircleOptions;
import net.doo.maps.model.Marker;
import net.doo.maps.model.MarkerOptions;
import net.doo.maps.model.Polygon;
import net.doo.maps.model.PolygonOptions;
import net.doo.maps.model.Polyline;
import net.doo.maps.model.PolylineOptions;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;

import java.util.ArrayList;

/**
 * Creates {@link net.doo.maps.model.DrawableComponent} and attaches them to maps
 */
public class DrawableComponentFactory {

	private final MapView map;

	private final ItemizedIconOverlay<MarkerOverlayItem> markersOverlay;

	private AnyMap.OnMarkerClickListener onMarkerClickListener = AnyMap.OnMarkerClickListener.NULL;

	public DrawableComponentFactory(MapView map) {
		this.map = map;

		markersOverlay = new ItemizedIconOverlay<>(
				map.getContext(),
				new ArrayList<MarkerOverlayItem>(),
				new ItemizedIconOverlay.OnItemGestureListener<MarkerOverlayItem>() {
					@Override
					public boolean onItemSingleTapUp(int index, MarkerOverlayItem item) {
						return onMarkerClickListener.onMarkerClick(item.marker);
					}

					@Override
					public boolean onItemLongPress(int index, MarkerOverlayItem item) {
						return false;
					}
				}
		);

		map.getOverlays().add(markersOverlay);
	}

	/**
	 * Adds marker to the map
	 *
	 * @return added {@link Marker} which is bound to the map.
	 */
	public Marker addMarker(MarkerOptions options) {
		return new OsmMarker(map, options, markersOverlay);
	}

	/**
	 * Adds circle to the map
	 *
	 * @return added {@link Circle} which is bound to the map.
	 */
	public Circle addCircle(CircleOptions options) {
		return new OsmCircle(map, options);
	}

	/**
	 * Adds polygon to the map.
	 *
	 * @return added {@link Polygon} which is bound to the map
	 */
	public Polygon addPolygon(PolygonOptions options) {
		return new OsmPolygon(map, options);
	}

	/**
	 * Adds polyline to the map.
	 *
	 * @return added {@link Polyline} which is bound to the map
	 */
	public Polyline addPolyline(PolylineOptions options) {
		return new OsmPolyline(map, options);
	}

	/**
	 * @param listener listener which will be invoked on marker clicks
	 */
	public void setOnMarkerClickListener(AnyMap.OnMarkerClickListener listener) {
		onMarkerClickListener = listener == null
				? AnyMap.OnMarkerClickListener.NULL
				: listener;
	}
}
