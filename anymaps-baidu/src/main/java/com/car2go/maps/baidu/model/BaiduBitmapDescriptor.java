/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.baidu.model;

import com.car2go.maps.model.BitmapDescriptor;

/**
 * @see BitmapDescriptor
 */
public class BaiduBitmapDescriptor implements BitmapDescriptor {
	public final com.baidu.mapapi.map.BitmapDescriptor wrappedDescriptor;

	public BaiduBitmapDescriptor(com.baidu.mapapi.map.BitmapDescriptor wrappedDescriptor) {
		this.wrappedDescriptor = wrappedDescriptor;
	}
}
