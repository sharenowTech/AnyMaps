/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.model;

/**
 * Entity which can be drawn on map
 */
public interface DrawableComponent {

	/**
	 * Changes visibility of the component
	 *
	 * @param visible {@code true} to make component visible.
	 *                {@code false} to make component invisible.
	 */
	void setVisible(boolean visible);

	/**
	 * Removes component from the map. If it's already removed, does nothing.
	 */
	void remove();

}
