/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.osm;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;

import net.doo.maps.MapContainerView;
import net.doo.maps.OnMapReadyCallback;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

/**
 * @see MapContainerView
 */
public class MapView extends MapContainerView {

	private OsmMap anyMap;

	public MapView(Context context) {
		super(context);

		initView(context, null);
	}

	public MapView(Context context, AttributeSet attrs) {
		super(context, attrs);

		initView(context, attrs);
	}

	private void initView(Context context, AttributeSet attrs) {
		org.osmdroid.views.MapView mapView = new org.osmdroid.views.MapView(context, null);

		addView(mapView);

		mapView.setTileSource(TileSourceFactory.MAPNIK);
		mapView.setMultiTouchControls(true);

		anyMap = new OsmMap(mapView);

		setClipToPadding(false);
		setLayerType(LAYER_TYPE_SOFTWARE, null);

		applyAttributes(context, attrs);
	}

	private void applyAttributes(Context context, AttributeSet attrs) {
		if (attrs == null) {
			return;
		}

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MapView);
		try {
			boolean liteMode = typedArray.getBoolean(R.styleable.MapView_anyMapLiteMode, false);

			anyMap.getUiSettings().setAllGesturesEnabled(!liteMode);
		} finally {
			typedArray.recycle();
		}
	}

	@Override
	public void getMapAsync(final OnMapReadyCallback callback) {
		post(new Runnable() {
			@Override
			public void run() {
				callback.onMapReady(anyMap);
			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Do nothing
	}

	@Override
	public void onResume() {
		// Do nothing
	}

	@Override
	public void onPause() {
		// Do nothing
	}

	@Override
	public void onDestroy() {
		// Do nothing
	}

	@Override
	public void onLowMemory() {
		// Do nothing
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Do nothing
	}

}
