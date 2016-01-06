/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * View container for an {@link AnyMap}.
 */
public abstract class MapContainerView extends FrameLayout {

	public MapContainerView(Context context) {
		super(context);
	}

	public MapContainerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * Gets the wrapped {@link AnyMap}.
	 *
	 * @return the map
	 */
	public abstract AnyMap getMap();

	/**
	 * Gets the wrapped {@link AnyMap} asynchronously.
	 *
	 * @param callback the callback to use when the map has been got
	 */
	public abstract void getMapAsync(OnMapReadyCallback callback);

	/**
	 * Propagate the onCreate lifecycle call to the AnyMap.
	 *
	 * @param savedInstanceState the savedInstanceState
	 */
	public abstract void onCreate(Bundle savedInstanceState);

	/**
	 * Propagate the onResume lifecycle call to the AnyMap.
	 */
	public abstract void onResume();

	/**
	 * Propagate the onPause lifecycle call to the AnyMap.
	 */
	public abstract void onPause();

	/**
	 * Propagate the onDestroy lifecycle call to the AnyMap.
	 */
	public abstract void onDestroy();

	/**
	 * Propagate the onLowMemory lifecycle call to the AnyMap.
	 */
	public abstract void onLowMemory();

	/**
	 * Propagate the onSaveInstanceState lifecycle call to the AnyMap.
	 *
	 * @param outState the outState
	 */
	public abstract void onSaveInstanceState(Bundle outState);

}
