/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.model;

import java.util.List;

/**
 * Draws polygon on the map. Might contain holes within the polygon.
 */
public interface Polygon extends DrawableComponent {

	/**
	 * @param holes holes within the polygon area. If holes are outside of the polygon, behavior
	 *              is undefined.
	 */
	void setHoles(List<List<LatLng>> holes);

	/**
	 * @return a snapshot of the vertices of this polygon at this time.
	 * The list returned is a copy of the list of vertices and so changes to the polygon's vertices
	 * will not be reflected by this list, nor will changes to this list be reflected by the polygon.
	 */
	List<LatLng> getPoints();

}
