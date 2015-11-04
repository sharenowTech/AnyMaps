/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter.factory.anymap;

import com.google.android.gms.maps.model.Marker;

import net.doo.maps.google.adapter.MarkerAdapter;
import net.doo.maps.google.adapter.factory.Mapper;

/**
 * Maps Google Marker to AnyMap Marker
 */
public class MarkerMapper implements Mapper<Marker, net.doo.maps.model.Marker> {

	@Override
	public net.doo.maps.model.Marker map(Marker input) {
		return new MarkerAdapter(input);
	}

}
