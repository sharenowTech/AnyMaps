/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.anymap;

import com.car2go.maps.google.adapter.CircleAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.google.android.gms.maps.model.Circle;

/**
 * Maps Google Circle to AnyMap Circle
 */
public class CircleMapper implements Mapper<Circle, com.car2go.maps.model.Circle> {

	@Override
	public com.car2go.maps.model.Circle map(Circle input) {
		return new CircleAdapter(input);
	}

}
