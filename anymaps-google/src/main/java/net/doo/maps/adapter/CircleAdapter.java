/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter;

import net.doo.maps.model.Circle;

/**
 * Adapts Google Circle to AnyMap Circle
 */
public class CircleAdapter implements Circle {

	private final com.google.android.gms.maps.model.Circle circle;

	public CircleAdapter(com.google.android.gms.maps.model.Circle circle) {
		this.circle = circle;
	}

	@Override
	public void setVisible(boolean visible) {
		circle.setVisible(visible);
	}

	@Override
	public void remove() {
		circle.remove();
	}

}
