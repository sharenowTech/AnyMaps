/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter;

import net.doo.maps.CameraUpdate;

/**
 * Adapts Google CameraUpdate to AnyMap CameraUpdate
 */
public class CameraUpdateAdapter implements CameraUpdate {

	public final com.google.android.gms.maps.CameraUpdate wrappedCameraUpdate;

	public CameraUpdateAdapter(com.google.android.gms.maps.CameraUpdate wrappedCameraUpdate) {
		this.wrappedCameraUpdate = wrappedCameraUpdate;
	}

}
