/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.model;

import android.support.annotation.ColorInt;

import java.util.ArrayList;
import java.util.List;

/**
 * Accumulates parameters which are required to create {@link Polyline} component.
 * Mutable.
 */
public class PolylineOptions {

	private int color;
	private float width;
	private final List<LatLng> points = new ArrayList<>();

	/**
	 * @param color color of the line
	 * @return same {@link PolylineOptions}
	 */
	public PolylineOptions color(@ColorInt int color) {
		this.color = color;
		return this;
	}

	/**
	 * @param width width of the line in pixels
	 * @return same {@link PolylineOptions}
	 */
	public PolylineOptions width(float width) {
		this.width = width;
		return this;
	}

	/**
	 * Adds point to polyline
	 *
	 * @return same {@link PolylineOptions}
	 */
	public PolylineOptions add(LatLng point) {
		points.add(point);
		return this;
	}

	/**
	 * Adds all points from list to polyline
	 *
	 * @return same {@link PolylineOptions}
	 */
	public PolylineOptions addAll(List<LatLng> points) {
		this.points.addAll(points);
		return this;
	}

	/**
	 * @see #color(int)
	 */
	public int getColor() {
		return color;
	}

	/**
	 * @see #width(float)
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @see #add(LatLng)
	 * @see #addAll(List)
	 */
	public List<LatLng> getPoints() {
		return points;
	}
}
