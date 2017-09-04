/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.model;

import android.support.annotation.ColorInt;

/**
 * Accumulates parameters which are required to create {@link Circle} component.
 * Mutable.
 */
public class CircleOptions {

	private LatLng center;
	private double radius;
	private int fillColor;
	private int strokeColor;
	private float strokeWidth;

	/**
	 * @param point center of the circle
	 * @return same {@link CircleOptions}
	 */
	public CircleOptions center(LatLng point) {
		center = point;
		return this;
	}

	/**
	 * @param radius radius of the circle in meters
	 * @return same {@link CircleOptions}
	 */
	public CircleOptions radius(double radius) {
		this.radius = radius;
		return this;
	}

	/**
	 * @param color color used to fill the circle
	 * @return same {@link CircleOptions}
	 */
	public CircleOptions fillColor(@ColorInt int color) {
		fillColor = color;
		return this;
	}

	/**
	 * @param color color of the circle outline (stroke)
	 * @return same {@link CircleOptions}
	 */
	public CircleOptions strokeColor(@ColorInt int color) {
		strokeColor = color;
		return this;
	}

	/**
	 * @param width width of the stroke in pixels
	 * @return same {@link CircleOptions}
	 */
	public CircleOptions strokeWidth(float width) {
		strokeWidth = width;
		return this;
	}

	/**
	 * @see #center(LatLng)
	 */
	public LatLng getCenter() {
		return center;
	}

	/**
	 * @see #radius(double)
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @see #fillColor(int)
	 */
	public int getFillColor() {
		return fillColor;
	}

	/**
	 * @see #strokeColor(int)
	 */
	public int getStrokeColor() {
		return strokeColor;
	}

	/**
	 * @see #strokeWidth(float)
	 */
	public float getStrokeWidth() {
		return strokeWidth;
	}
}
