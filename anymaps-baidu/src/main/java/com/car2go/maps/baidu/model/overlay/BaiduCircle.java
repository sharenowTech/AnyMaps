/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.baidu.model.overlay;

import com.car2go.maps.model.Circle;

public class BaiduCircle implements Circle {

	private final com.baidu.mapapi.map.Circle circle;

	public BaiduCircle(com.baidu.mapapi.map.Circle circle) {
		this.circle = circle;
	}

	@Override
	public void setVisible(final boolean visible) {
		if (circle.isVisible() != visible) {
			circle.setVisible(visible);
		}
	}

	@Override
	public void remove() {
		circle.remove();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaiduCircle)) return false;

		BaiduCircle that = (BaiduCircle) o;

		return circle.equals(that.circle);
	}

	@Override
	public int hashCode() {
		return circle.hashCode();
	}
}
