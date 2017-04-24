/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter;

import com.car2go.maps.Projection;
import com.car2go.maps.model.VisibleRegion;

/**
 * Adapts Google Map projection to AnyMap projection
 */
public class ProjectionAdapter implements Projection {

	private final com.google.android.gms.maps.Projection projection;

	public ProjectionAdapter(com.google.android.gms.maps.Projection projection) {
		this.projection = projection;
	}

	@Override
	public VisibleRegion getVisibleRegion() {
		return AnyMapAdapter.adapt(projection.getVisibleRegion());
	}

}
