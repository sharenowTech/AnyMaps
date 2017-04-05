/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * (Pseudo)Rectangular region on the map.
 * Immutable.
 */
public class LatLngBounds implements Parcelable {

	/**
	 * South-West point of the region.
	 */
	public final LatLng southwest;
	/**
	 * North-East point of the region.
	 */
	public final LatLng northeast;

	public LatLngBounds(LatLng southwest, LatLng northeast) {
		this.southwest = southwest;
		this.northeast = northeast;
	}

	protected LatLngBounds(Parcel in) {
		this.southwest = in.readParcelable(LatLng.class.getClassLoader());
		this.northeast = in.readParcelable(LatLng.class.getClassLoader());
	}

	/**
	 * @return {@link com.car2go.maps.model.LatLngBounds.Builder} for {@link LatLngBounds}
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * @return center of the region
	 */
	public LatLng getCenter() {
		// Implementation copied from original obfuscated version of LatLngBounds

		double var1 = (this.southwest.latitude + this.northeast.latitude) / 2.0D;
		double var3 = this.northeast.longitude;
		double var5 = this.southwest.longitude;
		double var7;
		if (var5 <= var3) {
			var7 = (var3 + var5) / 2.0D;
		} else {
			var7 = (var3 + 360.0D + var5) / 2.0D;
		}

		return new LatLng(var1, var7);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof LatLngBounds)) return false;

		LatLngBounds that = (LatLngBounds) o;

		return southwest.equals(that.southwest) && northeast.equals(that.northeast);
	}

	@Override
	public int hashCode() {
		int result = southwest.hashCode();
		result = 31 * result + northeast.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "LatLngBounds{" +
				"southwest=" + southwest +
				", northeast=" + northeast +
				'}';
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(this.southwest, 0);
		dest.writeParcelable(this.northeast, 0);
	}

	public static final Parcelable.Creator<LatLngBounds> CREATOR = new Parcelable.Creator<LatLngBounds>() {
		public LatLngBounds createFromParcel(Parcel source) {
			return new LatLngBounds(source);
		}

		public LatLngBounds[] newArray(int size) {
			return new LatLngBounds[size];
		}
	};

	/**
	 * Builds new instances of {@link LatLngBounds}
	 */
	public static class Builder {

		private double southWestLattitude = 1.0D / 0.0;
		private double northEastLattitude = -1.0D / 0.0;

		private double southWestLongitude = 0.0D / 0.0;
		private double northEastLongitude = 0.0D / 0.0;

		/**
		 * Ensures that given point will be within output bounds. Output bounds guaranteed to be
		 * as small as possible and enclose all given points.
		 *
		 * @return same {@link com.car2go.maps.model.LatLngBounds.Builder}
		 */
		public Builder include(LatLng point) {
			southWestLattitude = Math.min(southWestLattitude, point.latitude);
			northEastLattitude = Math.max(northEastLattitude, point.latitude);

			if (Double.isNaN(southWestLongitude)) {
				southWestLongitude = point.longitude;
				northEastLongitude = point.longitude;
			} else if (!withinBounds(point.longitude)) {
				if (degreeDifference(southWestLongitude, point.longitude) < degreeDifference(point.longitude, northEastLongitude)) {
					southWestLongitude = point.longitude;
				} else {
					northEastLongitude = point.longitude;
				}
			}

			return this;
		}

		private double degreeDifference(double first, double second) {
			return (first - second + 360.0D) % 360.0D;
		}

		private boolean withinBounds(double longitude) {
			return this.southWestLongitude <= this.northEastLongitude
					? this.southWestLongitude <= longitude && longitude <= this.northEastLongitude
					: this.southWestLongitude <= longitude || longitude <= this.northEastLongitude;
		}

		/**
		 * @return new instance of {@link LatLngBounds}
		 * @throws IllegalStateException if less than 2 unique points were specified
		 */
		public LatLngBounds build() {
			if (Double.isNaN(southWestLongitude)) {
				throw new IllegalStateException("No included points");
			}

			return new LatLngBounds(
					new LatLng(southWestLattitude, southWestLongitude),
					new LatLng(northEastLattitude, northEastLongitude)
			);
		}

	}

}
