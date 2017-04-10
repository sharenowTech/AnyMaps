/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.osm;

import android.graphics.Bitmap;

import com.car2go.maps.model.BitmapDescriptor;

/**
 * Holds {@link android.graphics.Bitmap} for {@link com.car2go.maps.osm.drawable.OsmMarker}
 */
public class OsmBitmapDescriptor implements BitmapDescriptor {

	public final Bitmap bitmap;

	public OsmBitmapDescriptor(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

}
