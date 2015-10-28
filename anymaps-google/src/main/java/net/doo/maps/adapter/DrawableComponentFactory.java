/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter;

import com.google.android.gms.maps.GoogleMap;

import net.doo.maps.model.Circle;
import net.doo.maps.model.CircleOptions;
import net.doo.maps.model.Marker;
import net.doo.maps.model.MarkerOptions;
import net.doo.maps.model.Polygon;
import net.doo.maps.model.PolygonOptions;
import net.doo.maps.model.Polyline;
import net.doo.maps.model.PolylineOptions;

/**
 * Factory of {@link net.doo.maps.model.DrawableComponent}. Created components are bound
 * to associated {@link GoogleMap}
 */
class DrawableComponentFactory {

	private final GoogleMap map;

	public DrawableComponentFactory(GoogleMap map) {
		this.map = map;
	}

	/**
	 * Adds marker to the map
	 *
	 * @return added {@link Marker} which is bound to the map.
	 */
	public Marker addMarker(MarkerOptions options) {
		com.google.android.gms.maps.model.MarkerOptions googleOptions = AnyMapAdapter.adapt(options);
		com.google.android.gms.maps.model.Marker marker = map.addMarker(googleOptions);

		return AnyMapAdapter.adapt(marker);
	}

	/**
	 * Adds circle to the map
	 *
	 * @return added {@link Circle} which is bound to the map.
	 */
	public Circle addCircle(CircleOptions options) {
		com.google.android.gms.maps.model.CircleOptions googleOptions = AnyMapAdapter.adapt(options);
		com.google.android.gms.maps.model.Circle circle = map.addCircle(googleOptions);

		return AnyMapAdapter.adapt(circle);
	}

	/**
	 * Adds polygon to the map.
	 *
	 * @return added {@link Polygon} which is bound to the map
	 */
	public Polygon addPolygon(PolygonOptions options) {
		com.google.android.gms.maps.model.PolygonOptions googleOptions = AnyMapAdapter.adapt(options);
		com.google.android.gms.maps.model.Polygon polygon = map.addPolygon(googleOptions);

		return AnyMapAdapter.adapt(polygon);
	}

	/**
	 * Adds polyline to the map.
	 *
	 * @return added {@link Polyline} which is bound to the map
	 */
	public Polyline addPolyline(PolylineOptions options) {
		com.google.android.gms.maps.model.PolylineOptions googleOptions = AnyMapAdapter.adapt(options);
		com.google.android.gms.maps.model.Polyline polyline = map.addPolyline(googleOptions);

		return AnyMapAdapter.adapt(polyline);
	}

}
