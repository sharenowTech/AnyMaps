/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter.factory.anymap;

import com.google.android.gms.maps.model.Circle;

import net.doo.maps.adapter.CircleAdapter;
import net.doo.maps.adapter.factory.Mapper;

/**
 * Maps Google Circle to AnyMap Circle
 */
public class CircleMapper implements Mapper<Circle, net.doo.maps.model.Circle> {

	@Override
	public net.doo.maps.model.Circle map(Circle input) {
		return new CircleAdapter(input);
	}

}
