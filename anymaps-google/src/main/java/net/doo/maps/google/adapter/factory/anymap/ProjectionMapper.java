/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter.factory.anymap;

import com.google.android.gms.maps.Projection;

import net.doo.maps.google.adapter.ProjectionAdapter;
import net.doo.maps.google.adapter.factory.Mapper;

/**
 * Maps Google Projection to AnyMap Projection
 */
public class ProjectionMapper implements Mapper<Projection, net.doo.maps.Projection> {

	@Override
	public net.doo.maps.Projection map(Projection input) {
		return new ProjectionAdapter(input);
	}

}
