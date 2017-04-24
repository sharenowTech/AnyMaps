/*
 * Copyright (c) 2017 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps;

import com.car2go.maps.model.VisibleRegion;

/**
 * Mimics Google Projection
 */
public interface Projection {

	VisibleRegion getVisibleRegion();

}
