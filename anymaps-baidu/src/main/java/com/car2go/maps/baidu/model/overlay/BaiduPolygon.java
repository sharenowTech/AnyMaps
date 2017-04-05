/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.baidu.model.overlay;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.PolygonOptions;
import com.baidu.mapapi.map.Stroke;
import com.car2go.maps.baidu.model.BaiduToModelConverter;
import com.car2go.maps.baidu.model.ModelToBaiduConverter;
import com.car2go.maps.model.LatLng;
import com.car2go.maps.model.Polygon;

import java.util.ArrayList;
import java.util.List;

public class BaiduPolygon implements Polygon {

	private com.baidu.mapapi.map.Polygon polygon;
	private List<com.baidu.mapapi.map.Polygon> holePolygons = new ArrayList<>();
	private BaiduMap map;

	public BaiduPolygon(BaiduMap map) {
		this.map = map;
	}

	public BaiduPolygon(BaiduMap map, com.baidu.mapapi.map.Polygon polygon) {
		this.map = map;
		this.polygon = polygon;
	}

	@Override
	public void setHoles(List<List<LatLng>> holes) {
		holePolygons.clear();
		for (List<LatLng> hole : holes) {
			com.baidu.mapapi.map.Polygon polygon = (com.baidu.mapapi.map.Polygon) map.addOverlay(new PolygonOptions()
					.fillColor(0x4499FF99)
					.points(ModelToBaiduConverter.convert(hole))
					.stroke(new Stroke(8, 0xa600aff8)));
			holePolygons.add(polygon);
		}
	}

	@Override
	public List<LatLng> getPoints() {
		return BaiduToModelConverter.convert(polygon.getPoints());
	}

	@Override
	public void setVisible(final boolean visible) {
		setVisisbleIfNeeded(polygon, visible);

		for (com.baidu.mapapi.map.Polygon hole : holePolygons) {
			setVisisbleIfNeeded(hole, visible);
		}
	}

	private void setVisisbleIfNeeded(com.baidu.mapapi.map.Polygon polygon, boolean visible) {
		if (polygon == null) {
			return;
		}
		if (polygon.isVisible() != visible) {
			polygon.setVisible(visible);
		}
	}

	@Override
	public void remove() {
		if (polygon != null) {
			polygon.remove();
		}

		for (com.baidu.mapapi.map.Polygon hole : holePolygons) {
			hole.remove();
		}
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
