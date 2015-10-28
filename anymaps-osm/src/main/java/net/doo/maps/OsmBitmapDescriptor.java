/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import android.graphics.Bitmap;

import net.doo.maps.model.BitmapDescriptor;

/**
 * Holds {@link android.graphics.Bitmap} for {@link net.doo.maps.drawable.OsmMarker}
 */
public class OsmBitmapDescriptor implements BitmapDescriptor {

	public final Bitmap bitmap;

	public OsmBitmapDescriptor(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

}
