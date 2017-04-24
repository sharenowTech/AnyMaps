/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.baidu.model;

import com.baidu.mapapi.map.Circle;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.car2go.maps.Projection;
import com.car2go.maps.baidu.model.overlay.BaiduCircle;
import com.car2go.maps.baidu.model.overlay.BaiduMarker;
import com.car2go.maps.baidu.model.overlay.BaiduPolygon;
import com.car2go.maps.baidu.model.overlay.BaiduPolyline;
import com.car2go.maps.model.CameraPosition;
import com.car2go.maps.model.VisibleRegion;

import java.util.ArrayList;
import java.util.List;


/**
 * Converts Baidu objects to model objects.
 */
public final class BaiduToModelConverter {

	private BaiduToModelConverter() {

	}

	public static CameraPosition convert(MapStatus mapStatus) {
		return new CameraPosition(convert(mapStatus.target), mapStatus.zoom);
	}

	public static com.car2go.maps.model.Marker convert(Marker marker) {
		return new BaiduMarker(marker);
	}

	public static com.car2go.maps.model.Circle convert(Circle circle) {
		return new BaiduCircle(circle);
	}

	public static com.car2go.maps.model.Polyline convert(Polyline polyline) {
		return new BaiduPolyline(polyline);
	}

	public static BaiduPolygon convert(com.baidu.mapapi.map.BaiduMap map, com.baidu.mapapi.map.Polygon polygon) {
		return new BaiduPolygon(map, polygon);
	}

	public static List<com.car2go.maps.model.LatLng> convert(List<LatLng> points) {
		List<com.car2go.maps.model.LatLng> converted = new ArrayList<>(points.size());
		for (LatLng point : points) {
			converted.add(convert(point));
		}
		return converted;
	}

	public static com.car2go.maps.model.LatLng convert(LatLng point) {
		return new com.car2go.maps.model.LatLng(point.latitude, point.longitude);
	}

	public static Projection convert(LatLngBounds bound) {
		return new BaiduProjection(
				new VisibleRegion(
						new com.car2go.maps.model.LatLngBounds(
								BaiduToModelConverter.convert(bound.southwest),
								BaiduToModelConverter.convert(bound.northeast)
						)
				)
		);
	}
}
