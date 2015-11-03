/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.baidu.model;

import net.doo.maps.model.BitmapDescriptor;

/**
 * @see net.doo.maps.model.BitmapDescriptor
 */
public class BaiduBitmapDescriptor implements BitmapDescriptor {
	public final com.baidu.mapapi.map.BitmapDescriptor wrappedDescriptor;

	public BaiduBitmapDescriptor(com.baidu.mapapi.map.BitmapDescriptor wrappedDescriptor) {
		this.wrappedDescriptor = wrappedDescriptor;
	}
}
