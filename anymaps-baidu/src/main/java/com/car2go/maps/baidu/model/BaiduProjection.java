/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.baidu.model;

import com.car2go.maps.Projection;
import com.car2go.maps.model.VisibleRegion;

/**
 * Adapts OpenStreetMap projection to AnyMap projection
 */
public class BaiduProjection implements Projection {

	private final VisibleRegion visibleRegion;

	public BaiduProjection(VisibleRegion visibleRegion) {
		this.visibleRegion = visibleRegion;
	}

	@Override
	public VisibleRegion getVisibleRegion() {
		return visibleRegion;
	}

}
