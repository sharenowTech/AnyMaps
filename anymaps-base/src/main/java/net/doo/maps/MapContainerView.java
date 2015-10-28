/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Container for {@link AnyMap}
 */
public abstract class MapContainerView extends FrameLayout implements MapPresenter {

	public MapContainerView(Context context) {
		super(context);
	}

	public MapContainerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

}
