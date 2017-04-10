/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Latitude/Longitude pair.
 * Immutable.
 */
public class LatLng implements Parcelable {

	/**
	 * Latitude on the map
	 */
	public final double latitude;
	/**
	 * Longitude on the map
	 */
	public final double longitude;

	public static LatLng fromLocation(Location location) {
		return new LatLng(location.getLatitude(), location.getLongitude());
	}

	public LatLng(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	protected LatLng(Parcel in) {
		this.latitude = in.readDouble();
		this.longitude = in.readDouble();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof LatLng)) {
			return false;
		}

		LatLng latLng = (LatLng) o;

		return Double.compare(latLng.latitude, latitude) == 0 && Double.compare(latLng.longitude, longitude) == 0;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "LatLng{" +
				"latitude=" + latitude +
				", longitude=" + longitude +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeDouble(this.latitude);
		dest.writeDouble(this.longitude);
	}

	public static final Creator<LatLng> CREATOR = new Creator<LatLng>() {
		public LatLng createFromParcel(Parcel source) {
			return new LatLng(source);
		}

		public LatLng[] newArray(int size) {
			return new LatLng[size];
		}
	};

}
