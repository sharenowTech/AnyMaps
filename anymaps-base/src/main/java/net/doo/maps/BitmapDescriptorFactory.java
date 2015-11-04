/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;

import net.doo.maps.model.BitmapDescriptor;

/**
 * Factory for creating BitmapDescriptors.
 */
public interface BitmapDescriptorFactory {
	/**
	 * @return new {@link BitmapDescriptor} from given {@link Bitmap}
	 */
	BitmapDescriptor fromBitmap(Bitmap bitmap);

	/**
	 * @return new {@link BitmapDescriptor} from given resource
	 */
	BitmapDescriptor fromResource(@DrawableRes int resourceId);
}
