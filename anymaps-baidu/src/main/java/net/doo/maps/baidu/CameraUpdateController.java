/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.baidu;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;

import net.doo.maps.CameraUpdate;
import net.doo.maps.baidu.model.BaiduCameraUpdate;
import net.doo.maps.baidu.model.ModelToBaiduConverter;
import net.doo.maps.model.CameraPosition;
import net.doo.maps.model.LatLng;

/**
 * Controls incoming {@link CameraUpdate} events
 */
public class CameraUpdateController {

	private final BaiduMap map;

	public CameraUpdateController(com.baidu.mapapi.map.BaiduMap map) {
		this.map = map;
	}

	public void animateMapStatus(BaiduCameraUpdate baiduCameraUpdate, boolean animate, Integer duration) {
		MapStatusUpdate mapStatusUpdate;
		if (baiduCameraUpdate.bounds != null) {
			mapStatusUpdate = MapStatusUpdateFactory.newLatLngBounds(ModelToBaiduConverter.convert(baiduCameraUpdate.bounds));
		} else if (baiduCameraUpdate.center == null) {
			throw new IllegalArgumentException("We either need a center to zoom to or some bounds");
		} else if (baiduCameraUpdate.zoom != null) {
			mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(ModelToBaiduConverter.convert(baiduCameraUpdate.center), baiduCameraUpdate.zoom);
		} else {
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

	public CameraPosition currentCameraPosition() {
		MapStatus mapStatus = map.getMapStatus();

		return new CameraPosition(
				new LatLng(mapStatus.target.latitude, mapStatus.target.longitude),
				mapStatus.zoom
		);
	}
}
