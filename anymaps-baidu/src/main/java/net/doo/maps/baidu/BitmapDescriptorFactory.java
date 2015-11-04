/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.baidu;

import android.graphics.Bitmap;

import net.doo.maps.baidu.model.BaiduBitmapDescriptor;
import net.doo.maps.model.BitmapDescriptor;

/**
 * Creates instances of {@link BitmapDescriptor}
 */
public class BitmapDescriptorFactory implements net.doo.maps.BitmapDescriptorFactory {

	@Override
	public BitmapDescriptor fromBitmap(Bitmap bitmap) {
		return new BaiduBitmapDescriptor(com.baidu.mapapi.map.BitmapDescriptorFactory.fromBitmap(bitmap));
	}

	@Override
	public BitmapDescriptor fromResource(int resourceId) {
		return new BaiduBitmapDescriptor(com.baidu.mapapi.map.BitmapDescriptorFactory.fromResource(resourceId));
	}

}
