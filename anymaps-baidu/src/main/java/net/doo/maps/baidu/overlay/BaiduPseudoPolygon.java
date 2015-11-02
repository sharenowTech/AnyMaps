/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.baidu.overlay;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;

import net.doo.maps.model.LatLng;
import net.doo.maps.model.Polygon;

import java.util.ArrayList;
import java.util.List;

public class BaiduPseudoPolygon implements Polygon {

	private final com.baidu.mapapi.map.Polyline polyline;
	private final BaiduMap map;
	private List<Polyline> holes = new ArrayList<>();

	public BaiduPseudoPolygon(BaiduMap map, Polyline polyline) {
		this.map = map;
		this.polyline = polyline;
	}

	@Override
	public void setHoles(List<List<LatLng>> holes) {
		for (List<LatLng> hole : holes) {
			Polyline polyline = (Polyline) map.addOverlay(new PolylineOptions()
							.points(Converter.convert(hole))
							.color(this.polyline.getColor())
							.width(this.polyline.getWidth())
			);
			this.holes.add(polyline);
		}
	}

	@Override
	public List<LatLng> getPoints() {
		return OutConverter.convert(polyline.getPoints());
	}

	@Override
	public void setVisible(final boolean visible) {
		if (polyline.isVisible() != visible) {
			polyline.setVisible(visible);

			if (holes.isEmpty()) {
				return;
			}

			for (Polyline hole : holes) {
				hole.setVisible(visible);
			}
		}
	}

	@Override
	public void remove() {
		polyline.remove();

		if (holes.isEmpty()) {
			return;
		}

		for (Polyline hole : holes) {
			hole.remove();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaiduPseudoPolygon)) return false;

		BaiduPseudoPolygon that = (BaiduPseudoPolygon) o;

		return polyline.equals(that.polyline);
	}

	@Override
	public int hashCode() {
		return polyline.hashCode();
	}
}
