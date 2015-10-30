/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.overlay;

import net.doo.maps.model.LatLng;
import net.doo.maps.model.Polygon;

import java.util.List;

public class BaiduPolygon implements Polygon {

	private final com.baidu.mapapi.map.Polygon polygon;

	public BaiduPolygon(com.baidu.mapapi.map.Polygon polygon) {
		this.polygon = polygon;
	}

	@Override
	public void setHoles(List<List<LatLng>> holes) {
		// TODO
	}

	@Override
	public List<LatLng> getPoints() {
		return OutConverter.convert(polygon.getPoints());
	}

	@Override
	public void setVisible(final boolean visible) {
		if (polygon.isVisible() != visible) {
			polygon.setVisible(visible);
		}
	}

	@Override
	public void remove() {
		polygon.remove();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaiduPolygon)) return false;

		BaiduPolygon that = (BaiduPolygon) o;

		return polygon.equals(that.polygon);
	}

	@Override
	public int hashCode() {
		return polygon.hashCode();
	}
}
