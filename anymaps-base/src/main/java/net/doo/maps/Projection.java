/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import net.doo.maps.model.VisibleRegion;

/**
 * Mimics Google Projection
 */
public interface Projection {

	VisibleRegion getVisibleRegion();

}
