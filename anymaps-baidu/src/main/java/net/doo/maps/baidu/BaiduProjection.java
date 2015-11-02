/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.baidu;

import net.doo.maps.Projection;
import net.doo.maps.model.VisibleRegion;

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
