/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.baidu.model;

import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.car2go.maps.model.BitmapDescriptor;
import com.car2go.maps.model.CircleOptions;
import com.car2go.maps.model.LatLng;
import com.car2go.maps.model.LatLngBounds;
import com.car2go.maps.model.MarkerOptions;
import com.car2go.maps.model.PolygonOptions;
import com.car2go.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts model objects to baidu objects.
 */
public final class ModelToBaiduConverter {

	private ModelToBaiduConverter() {

	}

	public static com.baidu.mapapi.model.LatLng convert(com.car2go.maps.model.LatLng position) {
		if (position == null) {
			return null;
		}
		return new com.baidu.mapapi.model.LatLng(position.latitude, position.longitude);
	}

	public static OverlayOptions convert(MarkerOptions options) {
		return new com.baidu.mapapi.map.MarkerOptions()
				.icon(convert(options.getIcon()))
				.position(ModelToBaiduConverter.convert(options.getPosition()))
				.anchor(options.getAnchorU(), options.getAnchorV())
				.visible(options.isVisible())
				.zIndex((int) options.getZ());
	}

	public static com.baidu.mapapi.map.BitmapDescriptor convert(BitmapDescriptor bitmapDescriptor) {
		return ((BaiduBitmapDescriptor) bitmapDescriptor).wrappedDescriptor;
	}


	public static com.baidu.mapapi.model.LatLngBounds convert(LatLngBounds bounds) {
		if (bounds == null) {
			return null;
		}
		return new com.baidu.mapapi.model.LatLngBounds.Builder()
				.include(convert(bounds.northeast))
				.include(convert(bounds.southwest))
				.build();
	}

	public static OverlayOptions convert(CircleOptions options) {
		return new com.baidu.mapapi.map.CircleOptions()
				.center(convert(options.getCenter()))
				.fillColor(options.getFillColor())
				.radius((int) Math.round(options.getRadius()))
				.stroke(new Stroke(Math.round(options.getStrokeWidth()), options.getStrokeColor()));
	}

	public static OverlayOptions convert(PolylineOptions options) {
		return new com.baidu.mapapi.map.PolylineOptions()
				.color(options.getColor())
				.width(Math.round(options.getWidth()))
				.points(convert(options.getPoints()));
	}

	public static List<com.baidu.mapapi.model.LatLng> convert(List<LatLng> points) {
		List<com.baidu.mapapi.model.LatLng> converted = new ArrayList<>(points.size());
		for (LatLng point : points) {
			converted.add(convert(point));
		}
		return converted;
	}

	public static OverlayOptions convert(PolygonOptions options) {
		return new com.baidu.mapapi.map.PolygonOptions()
				.points(convert(options.getPoints()))
				.stroke(new Stroke(Math.round(options.getStrokeWidth()), options.getStrokeColor()))
				.fillColor(options.getFillColor());
	}
}
