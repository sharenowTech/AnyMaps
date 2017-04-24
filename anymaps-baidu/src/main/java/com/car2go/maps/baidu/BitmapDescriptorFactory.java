/*
 * Copyright (c) 2017 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.baidu;

import android.graphics.Bitmap;

import com.car2go.maps.baidu.model.BaiduBitmapDescriptor;
import com.car2go.maps.model.BitmapDescriptor;

/**
 * Creates instances of {@link BitmapDescriptor}
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
		return new BaiduBitmapDescriptor(com.baidu.mapapi.map.BitmapDescriptorFactory.fromBitmap(bitmap));
	}

	@Override
	public BitmapDescriptor fromResource(int resourceId) {
		return new BaiduBitmapDescriptor(com.baidu.mapapi.map.BitmapDescriptorFactory.fromResource(resourceId));
	}

}
