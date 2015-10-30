/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import net.doo.maps.model.BitmapDescriptor;

/**
 * Creates instances of {@link BitmapDescriptor}
 */
public final class BitmapDescriptorFactory {

//	private static Context context;

	private BitmapDescriptorFactory() {
	}

//	static void initialize(Context context) {
//		BitmapDescriptorFactory.context = context.getApplicationContext();
//	}

	/**
	 * @return new {@link BitmapDescriptor} from given {@link Bitmap}
	 */
	public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
//		ensureInitialized();

		return new BaiduBitmapDescriptor(com.baidu.mapapi.map.BitmapDescriptorFactory.fromBitmap(bitmap));
//		return new OsmBitmapDescriptor(bitmap);
	}

	/**
	 * @return new {@link BitmapDescriptor} from given resource
	 */
	public static BitmapDescriptor fromResource(int resourceId) {
//		ensureInitialized();

		return new BaiduBitmapDescriptor(com.baidu.mapapi.map.BitmapDescriptorFactory.fromResource(resourceId));
//		return new OsmBitmapDescriptor(
//				BitmapFactory.decodeResource(context.getResources(), resourceId)
//		);
	}

//	private static void ensureInitialized() {
//		if (context == null) {
//			throw new IllegalStateException("Not initialized. Did you forgot to call MapsInitializer?");
//		}
//	}

}
