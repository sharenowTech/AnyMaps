/*
 * Copyright (c) 2017 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.baidu;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.car2go.maps.CameraUpdate;
import com.car2go.maps.baidu.model.BaiduCameraUpdate;
import com.car2go.maps.baidu.model.ModelToBaiduConverter;
import com.car2go.maps.model.CameraPosition;
import com.car2go.maps.model.LatLng;

/**
 * Controls incoming {@link CameraUpdate} events
 */
public class CameraUpdateController {

	private final BaiduMap map;

	public CameraUpdateController(com.baidu.mapapi.map.BaiduMap map) {
		this.map = map;
	}

	/**
	 * Changes the current map status with the given parameters.
	 *
	 * @param baiduCameraUpdate the camera update to perform
	 * @param animate           set to true, if the change should be animated
	 * @param duration          the animation duration, may be null to use the default
	 */
	public void animateMapStatus(BaiduCameraUpdate baiduCameraUpdate, boolean animate, Integer duration) {
		if (baiduCameraUpdate.bounds == null && baiduCameraUpdate.center == null && baiduCameraUpdate.zoom == null) {
			throw new IllegalArgumentException("We either need a center, some bounds or a zoom in order to animate.");
		}

		MapStatusUpdate mapStatusUpdate;
		if (baiduCameraUpdate.bounds != null) {
			// Zoom to given bounds
			mapStatusUpdate = MapStatusUpdateFactory.newLatLngBounds(ModelToBaiduConverter.convert(baiduCameraUpdate.bounds));
		} else if (baiduCameraUpdate.center == null) {
			// Just zoom in or out
			mapStatusUpdate = MapStatusUpdateFactory.zoomTo(baiduCameraUpdate.zoom);
		} else if (baiduCameraUpdate.zoom != null) {
			// Go to the new center & zoom
			mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(ModelToBaiduConverter.convert(baiduCameraUpdate.center), baiduCameraUpdate.zoom);
		} else {
			// Just go to the new center
			mapStatusUpdate = MapStatusUpdateFactory.newLatLng(ModelToBaiduConverter.convert(baiduCameraUpdate.center));
		}

		if (!animate) {
			map.setMapStatus(mapStatusUpdate);
		} else if (duration == null) {
			map.animateMapStatus(mapStatusUpdate);
		} else {
			map.animateMapStatus(mapStatusUpdate, duration);
		}
	}

	/**
	 * Gets the current camera position.
	 *
	 * @return the camera position
	 */
	public CameraPosition currentCameraPosition() {
		MapStatus mapStatus = map.getMapStatus();

		return new CameraPosition(
				new LatLng(mapStatus.target.latitude, mapStatus.target.longitude),
				mapStatus.zoom
		);
	}
}
