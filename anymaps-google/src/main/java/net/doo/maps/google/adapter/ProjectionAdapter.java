/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter;

import net.doo.maps.Projection;
import net.doo.maps.model.VisibleRegion;

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
