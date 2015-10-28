/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import net.doo.maps.model.VisibleRegion;

/**
 * Adapts OpenStreetMap projection to AnyMap projection
 */
public class OsmProjection implements Projection {

	private final VisibleRegion visibleRegion;

	public OsmProjection(VisibleRegion visibleRegion) {
		this.visibleRegion = visibleRegion;
	}

	@Override
	public VisibleRegion getVisibleRegion() {
		return visibleRegion;
	}

}
