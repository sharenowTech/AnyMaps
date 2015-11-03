/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.baidu.overlay;

import com.baidu.mapapi.map.Circle;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.Polygon;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.model.LatLng;

import net.doo.maps.model.CameraPosition;

import java.util.ArrayList;
import java.util.List;


public final class OutConverter {

	private OutConverter() {

	}

	public static CameraPosition convert(MapStatus mapStatus) {
		return new CameraPosition(convert(mapStatus.target), mapStatus.zoom);
	}

	public static net.doo.maps.model.Marker convert(Marker marker) {
		return new BaiduMarker(marker);
	}

	public static net.doo.maps.model.Circle convert(Circle circle) {
		return new BaiduCircle(circle);
	}

	public static net.doo.maps.model.Polyline convert(Polyline polyline) {
		return new BaiduPolyline(polyline);
	}

	public static BaiduPolygon convert(Polygon polygon) {
		return new BaiduPolygon(polygon);
	}

	public static List<net.doo.maps.model.LatLng> convert(List<LatLng> points) {
		List<net.doo.maps.model.LatLng> converted = new ArrayList<>(points.size());
		for (LatLng point : points) {
			converted.add(convert(point));
		}
		return converted;
	}

	public static net.doo.maps.model.LatLng convert(LatLng point) {
		return new net.doo.maps.model.LatLng(point.latitude, point.longitude);
	}
}
