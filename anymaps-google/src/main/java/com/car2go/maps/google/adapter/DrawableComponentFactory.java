/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter;

import com.car2go.maps.model.Circle;
import com.car2go.maps.model.CircleOptions;
import com.car2go.maps.model.Marker;
import com.car2go.maps.model.MarkerOptions;
import com.car2go.maps.model.Polygon;
import com.car2go.maps.model.PolygonOptions;
import com.car2go.maps.model.Polyline;
import com.car2go.maps.model.PolylineOptions;
import com.google.android.gms.maps.GoogleMap;

/**
 * Factory of {@link com.car2go.maps.model.DrawableComponent}. Created components are bound
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
