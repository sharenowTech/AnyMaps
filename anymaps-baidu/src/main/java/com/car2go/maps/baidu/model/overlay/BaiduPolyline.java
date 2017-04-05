/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.baidu.model.overlay;


import com.car2go.maps.model.Polyline;

public class BaiduPolyline implements Polyline {

	private final com.baidu.mapapi.map.Polyline polyline;

	public BaiduPolyline(com.baidu.mapapi.map.Polyline polyline) {
		this.polyline = polyline;
	}

	@Override
	public void setVisible(final boolean visible) {
		if (polyline.isVisible() != visible) {
			polyline.setVisible(visible);
		}
	}

	@Override
	public void remove() {
		polyline.remove();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaiduPolyline)) return false;

		BaiduPolyline that = (BaiduPolyline) o;

		return polyline.equals(that.polyline);
	}

	@Override
	public int hashCode() {
		return polyline.hashCode();
	}
}
