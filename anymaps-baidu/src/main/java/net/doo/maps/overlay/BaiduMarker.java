/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.overlay;

import net.doo.maps.model.BitmapDescriptor;
import net.doo.maps.model.LatLng;
import net.doo.maps.model.Marker;

public class BaiduMarker implements Marker {

	private final com.baidu.mapapi.map.Marker marker;

	public BaiduMarker(com.baidu.mapapi.map.Marker marker) {
		this.marker = marker;
	}

	@Override
	public void setIcon(BitmapDescriptor icon) {
		marker.setIcon(Converter.convert(icon));
	}

	@Override
	public LatLng getPosition() {
		return OutConverter.convert(marker.getPosition());
	}

	@Override
	public void showInfoWindow() {
		// Do nothing
	}

	@Override
	public void setVisible(final boolean visible) {
		if (marker.isVisible() != visible) {
			marker.setVisible(visible);
		}
	}

	@Override
	public void remove() {
		marker.remove();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BaiduMarker)) return false;

		BaiduMarker that = (BaiduMarker) o;

		return marker.equals(that.marker);
	}

	@Override
	public int hashCode() {
		return marker.hashCode();
	}
}
