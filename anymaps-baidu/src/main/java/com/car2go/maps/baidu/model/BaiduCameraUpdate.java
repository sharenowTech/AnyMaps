/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.baidu.model;

import com.car2go.maps.CameraUpdate;
import com.car2go.maps.model.LatLng;
import com.car2go.maps.model.LatLngBounds;

/**
 * Contains information about pending map camera update
 */
public class BaiduCameraUpdate implements CameraUpdate {

	public final LatLng center;
	public final Float zoom;
	public final LatLngBounds bounds;
	public final Integer padding;

	private BaiduCameraUpdate(Builder builder) {
		center = builder.center;
		zoom = builder.zoom;
		bounds = builder.bounds;
		padding = builder.padding;
	}

	/**
	 * Builder for {@link BaiduCameraUpdate}
	 */
	public static final class Builder {
		private LatLng center;
		private Float zoom;
		private LatLngBounds bounds;
		private Integer padding;

		public Builder() {
		}

		/**
		 * Assigns new center of the camera. Can't be used together with {@link #bounds}
		 *
		 * @param center new center of the camera
		 * @return same {@link BaiduCameraUpdate.Builder}
		 */
		public Builder center(LatLng center) {
			this.center = center;
			return this;
		}

		/**
		 * Assigns new zoom level of the camera. Can't be used together with {@link #bounds}
		 *
		 * @param zoom new zoom level of the camera
		 * @return same {@link BaiduCameraUpdate.Builder}
		 */
		public Builder zoom(Float zoom) {
			this.zoom = zoom;
			return this;
		}

		/**
		 * Assigns new displayed bounds of the camera. Can't be used together with
		 * {@link #center(LatLng)} or {@link #zoom(Float)}
		 *
		 * @param bounds new bounds displayed by the camera
		 * @return same {@link BaiduCameraUpdate.Builder}
		 */
		public Builder bounds(LatLngBounds bounds) {
			this.bounds = bounds;
			return this;
		}

		/**
		 * Used in conjunction with {@link #bounds(LatLngBounds)}
		 *
		 * @param padding padding in pixels from the sides of the displayed bounds.
		 * @return same {@link BaiduCameraUpdate.Builder}
		 */
		public Builder padding(Integer padding) {
			this.padding = padding;
			return this;
		}

		/**
		 * @return new {@link BaiduCameraUpdate}
		 */
		public BaiduCameraUpdate build() {
			if ((center != null || zoom != null) && bounds != null) {
				throw new IllegalStateException("Conflicting parameters: center/zoom and bounds");
			}

			return new BaiduCameraUpdate(this);
		}

	}
}

