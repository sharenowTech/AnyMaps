/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;

import net.doo.maps.AnyMap;
import net.doo.maps.MapContainerView;
import net.doo.maps.OnMapReadyCallback;
import net.doo.maps.google.adapter.GoogleMapAdapter;

/**
 * @see net.doo.maps.MapContainerView
 */
public class MapView extends MapContainerView {

	private com.google.android.gms.maps.MapView mapView;

	private AnyMap map;

	public MapView(Context context) {
		super(context);

		initView(context, null);
	}

	public MapView(Context context, AttributeSet attrs) {
		super(context, attrs);

		initView(context, attrs);
	}

	private void initView(Context context, AttributeSet attrs) {
		GoogleMapOptions googleMapOptions = readOptions(context, attrs);

		mapView = new com.google.android.gms.maps.MapView(context, googleMapOptions);

		addView(mapView);
	}

	private GoogleMapOptions readOptions(Context context, AttributeSet attrs) {
		GoogleMapOptions options = new GoogleMapOptions();

		if (attrs == null) {
			return options;
		}

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MapView);
		try {
			options = options.liteMode(
					typedArray.getBoolean(R.styleable.MapView_anyMapLiteMode, false)
			);
		} finally {
			typedArray.recycle();
		}

		return options;
	}

	@Override
	public void getMapAsync(final OnMapReadyCallback callback) {
		if (map != null) {
			callback.onMapReady(map);
			return;
		}

		mapView.getMapAsync(new com.google.android.gms.maps.OnMapReadyCallback() {
			@Override
			public void onMapReady(GoogleMap googleMap) {
				if (map == null) {
					map = new GoogleMapAdapter(googleMap);
				}

				callback.onMapReady(map);
			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		mapView.onCreate(savedInstanceState);
	}

	@Override
	public void onResume() {
		mapView.onResume();
	}

	@Override
	public void onPause() {
		mapView.onPause();
	}

	@Override
	public void onDestroy() {
		if (map != null) {
			map.setMyLocationEnabled(false);
		}

		mapView.onDestroy();
	}

	@Override
	public void onLowMemory() {
		mapView.onLowMemory();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		mapView.onSaveInstanceState(outState);
	}

}
