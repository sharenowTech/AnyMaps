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
 * Accumulates parameters which are required to create {@link Polygon} component.
 * Mutable.
 */
public class PolygonOptions {

	private final List<LatLng> points = new ArrayList<>();
	private int fillColor;
	private float strokeWidth;
	private int strokeColor;
	private boolean outsider = false;

	/**
	 * @param color color used to fill the polygon
	 * @return same {@link PolygonOptions}
	 */
	public PolygonOptions fillColor(@ColorInt int color) {
		fillColor = color;
		return this;
	}

	/**
	 * @param width width of the polygon outline in pixels.
	 * @return same {@link PolygonOptions}
	 */
	public PolygonOptions strokeWidth(float width) {
		strokeWidth = width;
		return this;
	}

	/**
	 * @param color color of the polygon outline
	 * @return same {@link PolygonOptions}
	 */
	public PolygonOptions strokeColor(@ColorInt int color) {
		strokeColor = color;
		return this;
	}

	/**
	 * @param outsider {@code true} to invert filling of this polygon. That is, filling everything
	 *                 with color except for the holes. {@code false} for normal drawing routine.
	 * @return same {@link PolygonOptions}
	 */
	public PolygonOptions outsider(boolean outsider) {
		this.outsider = outsider;
		return this;
	}

	/**
	 * Adds given point to the polygon
	 *
	 * @return same {@link PolygonOptions}
	 */
	public PolygonOptions add(LatLng point) {
		points.add(point);
		return this;
	}

	/**
	 * Adds all points from list to the polygon
	 *
	 * @return same {@link PolygonOptions}
	 */
	public PolygonOptions addAll(List<LatLng> points) {
		this.points.addAll(points);
		return this;
	}

	/**
	 * @see #fillColor(int)
	 */
	public int getFillColor() {
		return fillColor;
	}

	/**
	 * @see #strokeWidth(float)
	 */
	public float getStrokeWidth() {
		return strokeWidth;
	}

	/**
	 * @see #strokeColor(int)
	 */
	public int getStrokeColor() {
		return strokeColor;
	}

	/**
	 * @see #outsider(boolean)
	 */
	public boolean isOutsider() {
		return outsider;
	}

	/**
	 * @see #add(LatLng)
	 * @see #addAll(List)
	 */
	public List<LatLng> getPoints() {
		return points;
	}

}
