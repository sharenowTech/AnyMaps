/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.baidu;

import net.doo.maps.model.BitmapDescriptor;

/**
 * Created by c.dehning on 01/09/15.
 */
public class BaiduBitmapDescriptor implements BitmapDescriptor {
	public final com.baidu.mapapi.map.BitmapDescriptor wrappedDescriptor;

	public BaiduBitmapDescriptor(com.baidu.mapapi.map.BitmapDescriptor wrappedDescriptor) {
		this.wrappedDescriptor = wrappedDescriptor;
	}
}
