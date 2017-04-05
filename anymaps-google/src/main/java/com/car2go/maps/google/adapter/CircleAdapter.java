/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter;

import com.car2go.maps.model.Circle;

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
