/*
 * Copyright (c) 2016 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.model;

import android.graphics.Bitmap;

/**
 * Draws marker (with icon) on the map
 */
public interface Marker extends DrawableComponent {

	/**
	 * Changes icon of the marker to given {@link Bitmap}
	 */
	void setIcon(BitmapDescriptor icon);

	/**
	 * @return current position of the marker
	 */
	LatLng getPosition();

	/**
	 * Shows information window associated with this marker, if any.
	 */
	void showInfoWindow();

	/**
	 * Sets the rotation of the marker.
	 *
	 * @param rotation the rotation value
	 */
	void setRotation(float rotation);

	/**
	 * Sets the Z index of the marker
	 *
	 * @param z z index of the marker
	 */
	void setZ(int z);

}