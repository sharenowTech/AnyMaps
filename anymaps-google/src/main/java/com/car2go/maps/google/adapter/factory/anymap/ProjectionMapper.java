/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.anymap;

import com.car2go.maps.google.adapter.ProjectionAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.google.android.gms.maps.Projection;

/**
 * Maps Google Projection to AnyMap Projection
 */
public class ProjectionMapper implements Mapper<Projection, com.car2go.maps.Projection> {

	@Override
	public com.car2go.maps.Projection map(Projection input) {
		return new ProjectionAdapter(input);
	}

}
