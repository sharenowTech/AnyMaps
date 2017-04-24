/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;

import com.car2go.maps.google.adapter.BitmapDescriptorAdapter;
import com.car2go.maps.model.BitmapDescriptor;

/**
 * Creates instances of {@link com.car2go.maps.model.BitmapDescriptor}
 */
public class BitmapDescriptorFactory implements com.car2go.maps.BitmapDescriptorFactory {

	private static final BitmapDescriptorFactory instance = new BitmapDescriptorFactory();

	private BitmapDescriptorFactory() {
	}

	public static BitmapDescriptorFactory getInstance() {
		return instance;
	}

	@Override
	public BitmapDescriptor fromBitmap(Bitmap bitmap) {
		return new BitmapDescriptorAdapter(
				com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(bitmap)
		);
	}

	@Override
	public BitmapDescriptor fromResource(@DrawableRes int resourceId) {
		return new BitmapDescriptorAdapter(
				com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(resourceId)
		);
	}

}
