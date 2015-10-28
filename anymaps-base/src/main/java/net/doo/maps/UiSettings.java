/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

/**
 * Mimics Google UiSettings
 */
public interface UiSettings {

	void setAllGesturesEnabled(boolean enabled);

	void setMyLocationButtonEnabled(boolean enabled);

	void setMapToolbarEnabled(boolean enabled);

}
