/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;

import net.doo.maps.adapter.BitmapDescriptorAdapter;
import net.doo.maps.model.BitmapDescriptor;

/**
 * Creates instances of {@link net.doo.maps.model.BitmapDescriptor}
 */
public class BitmapDescriptorFactory {

	private BitmapDescriptorFactory() {
	}

	/**
	 * @return new {@link BitmapDescriptor} from given {@link Bitmap}
	 */
	public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
		return new BitmapDescriptorAdapter(
				com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(bitmap)
		);
	}

	/**
	 * @return new {@link BitmapDescriptor} from given resource
	 */
	public static BitmapDescriptor fromResource(@DrawableRes int resourceId) {
		return new BitmapDescriptorAdapter(
				com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(resourceId)
		);
	}

}
