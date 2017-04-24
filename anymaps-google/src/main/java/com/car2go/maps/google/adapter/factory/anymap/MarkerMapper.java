/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.anymap;

import com.car2go.maps.google.adapter.MarkerAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.google.android.gms.maps.model.Marker;

/**
 * Maps Google Marker to AnyMap Marker
 */
public class MarkerMapper implements Mapper<Marker, com.car2go.maps.model.Marker> {

	@Override
	public com.car2go.maps.model.Marker map(Marker input) {
		return new MarkerAdapter(input);
	}

}
