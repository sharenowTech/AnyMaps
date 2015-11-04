/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.osm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import net.doo.maps.model.BitmapDescriptor;

/**
 * Creates instances of {@link BitmapDescriptor}
 */
public class BitmapDescriptorFactory implements net.doo.maps.BitmapDescriptorFactory {

	private static Context context;

	static void initialize(Context context) {
		BitmapDescriptorFactory.context = context.getApplicationContext();
	}

	@Override
	public BitmapDescriptor fromBitmap(Bitmap bitmap) {
		ensureInitialized();

		return new OsmBitmapDescriptor(bitmap);
	}

	@Override
	public BitmapDescriptor fromResource(int resourceId) {
		ensureInitialized();

		return new OsmBitmapDescriptor(
				BitmapFactory.decodeResource(context.getResources(), resourceId)
		);
	}

	private static void ensureInitialized() {
		if (context == null) {
			throw new IllegalStateException("Not initialized. Did you forgot to call MapsInitializer?");
		}
	}

}
