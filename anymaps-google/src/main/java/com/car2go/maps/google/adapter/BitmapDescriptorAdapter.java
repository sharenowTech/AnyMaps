/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter;

import com.car2go.maps.model.BitmapDescriptor;

/**
 * Adapts Google BitmapDescriptor to AnyMap BitmapDescriptor
 */
public class BitmapDescriptorAdapter implements BitmapDescriptor {

	public final com.google.android.gms.maps.model.BitmapDescriptor wrappedDescriptor;

	public BitmapDescriptorAdapter(com.google.android.gms.maps.model.BitmapDescriptor wrappedDescriptor) {
		this.wrappedDescriptor = wrappedDescriptor;
	}

}
