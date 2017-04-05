/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Visible region on the map.
 * Immutable.
 */
public class VisibleRegion implements Parcelable {

	/**
	 * Currently visible bounds.
	 */
	public final LatLngBounds latLngBounds;

	public VisibleRegion(LatLngBounds latLngBounds) {
		this.latLngBounds = latLngBounds;
	}

	protected VisibleRegion(Parcel in) {
		this.latLngBounds = in.readParcelable(LatLngBounds.class.getClassLoader());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof VisibleRegion)) {
			return false;
		}

		VisibleRegion that = (VisibleRegion) o;

		return latLngBounds.equals(that.latLngBounds);
	}

	@Override
	public int hashCode() {
		return latLngBounds.hashCode();
	}

	@Override
	public String toString() {
		return "VisibleRegion{" +
				"latLngBounds=" + latLngBounds +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.latLngBounds, flags);
	}

	public static final Parcelable.Creator<VisibleRegion> CREATOR = new Parcelable.Creator<VisibleRegion>() {
		public VisibleRegion createFromParcel(Parcel source) {
			return new VisibleRegion(source);
		}

		public VisibleRegion[] newArray(int size) {
			return new VisibleRegion[size];
		}
	};

}
