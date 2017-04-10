/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Contains information about camera position on the map.
 * Immutable.
 */
public class CameraPosition implements Parcelable {

	/**
	 * Center of the camera viewport
	 */
	public final LatLng target;
	/**
	 * Zoom level of the camera
	 */
	public final float zoom;

	public CameraPosition(LatLng target, float zoom) {
		this.target = target;
		this.zoom = zoom;
	}

	protected CameraPosition(Parcel in) {
		this.target = in.readParcelable(LatLng.class.getClassLoader());
		this.zoom = in.readFloat();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CameraPosition)) {
			return false;
		}

		CameraPosition that = (CameraPosition) o;

		return Float.compare(that.zoom, zoom) == 0 && target.equals(that.target);

	}

	@Override
	public int hashCode() {
		int result = target.hashCode();
		result = 31 * result + (zoom != +0.0f ? Float.floatToIntBits(zoom) : 0);
		return result;
	}

	@Override
	public String toString() {
		return "CameraPosition{" +
				"target=" + target +
				", zoom=" + zoom +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.target, flags);
		dest.writeFloat(this.zoom);
	}

	public static final Creator<CameraPosition> CREATOR = new Creator<CameraPosition>() {
		public CameraPosition createFromParcel(Parcel source) {
			return new CameraPosition(source);
		}

		public CameraPosition[] newArray(int size) {
			return new CameraPosition[size];
		}
	};

}
