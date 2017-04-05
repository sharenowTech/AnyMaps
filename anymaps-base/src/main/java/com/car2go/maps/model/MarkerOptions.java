/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.model;

/**
 * Accumulates parameters which are required to create {@link Marker} component.
 * Mutable.
 */
public class MarkerOptions {

	private float alpha = 1f;
	private LatLng position;
	private boolean visible = true;
	private float anchorU = 0f;
	private float anchorV = 0f;
	private BitmapDescriptor icon;
	private int z = 0;

	/**
	 * @param alpha alpha-level of the marker. In range [0..1]. Default value is 1.
	 * @return same {@link MarkerOptions}
	 */
	public MarkerOptions alpha(float alpha) {
		this.alpha = alpha;
		return this;
	}

	/**
	 * @param position position of the marker's anchor on the map
	 * @return same {@link MarkerOptions}
	 */
	public MarkerOptions position(LatLng position) {
		this.position = position;
		return this;
	}

	/**
	 * @param visible {@code true} to make marker visible by default. {@code false} to make marker
	 *                invisible by default. Default value is {@code true}.
	 * @return same {@link MarkerOptions}
	 */
	public MarkerOptions visible(boolean visible) {
		this.visible = visible;
		return this;
	}

	/**
	 * Specifies anchor of the marker (which part of marker's icon is considered position of the
	 * marker on the map). (0, 0) denotes top left corner. (1, 1) denotes bottom right corner.
	 *
	 * @param u U coordinate of the anchor relatively to the icon. Default value is 0.
	 * @param v V coordinate of the anchor relatively to the icon. Default value is 0.
	 * @return same {@link MarkerOptions}
	 */
	public MarkerOptions anchor(float u, float v) {
		anchorU = u;
		anchorV = v;
		return this;
	}

	/**
	 * @param icon icon of the marker
	 * @return same {@link MarkerOptions}
	 */
	public MarkerOptions icon(BitmapDescriptor icon) {
		this.icon = icon;
		return this;
	}

	public MarkerOptions z(int z) {
		this.z = z;
		return this;
	}

	/**
	 * @see #alpha(float)
	 */
	public float getAlpha() {
		return alpha;
	}

	/**
	 * @see #position(LatLng)
	 */
	public LatLng getPosition() {
		return position;
	}

	/**
	 * @see #visible(boolean)
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @see #anchor(float, float)
	 */
	public float getAnchorU() {
		return anchorU;
	}

	/**
	 * @see #anchor(float, float)
	 */
	public float getAnchorV() {
		return anchorV;
	}

	/**
	 * @see #icon(BitmapDescriptor)
	 */
	public BitmapDescriptor getIcon() {
		return icon;
	}

	/**
	 * @see #z(int)
	 */
	public int getZ() {
		return z;
	}

}
