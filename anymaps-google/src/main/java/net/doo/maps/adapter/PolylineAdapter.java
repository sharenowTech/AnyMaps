/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter;

import net.doo.maps.model.Polyline;

/**
 * Adapts Google Polyline to AnyMap Polyline
 */
public class PolylineAdapter implements Polyline {

	private final com.google.android.gms.maps.model.Polyline polyline;

	public PolylineAdapter(com.google.android.gms.maps.model.Polyline polyline) {
		this.polyline = polyline;
	}

	@Override
	public void setVisible(boolean visible) {
		polyline.setVisible(visible);
	}

	@Override
	public void remove() {
		polyline.remove();
	}

}
