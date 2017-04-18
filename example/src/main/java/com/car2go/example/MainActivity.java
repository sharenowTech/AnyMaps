/*
 * Copyright (c) 2017 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.car2go.maps.AnyMap;
import com.car2go.maps.MapContainerView;
import com.car2go.maps.OnMapReadyCallback;

public class MainActivity extends AppCompatActivity {

	private MapContainerView mapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mapView = (MapContainerView) findViewById(R.id.map_view);

		mapView.onCreate(savedInstanceState);

		mapView.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(AnyMap anyMap) {
				// Do something with map
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		mapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();

		mapView.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		mapView.onDestroy();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		mapView.onSaveInstanceState(outState);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();

		mapView.onLowMemory();
	}

}
