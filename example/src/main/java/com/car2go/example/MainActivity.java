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
import com.car2go.maps.model.LatLng;
import com.car2go.maps.model.MarkerOptions;
import com.car2go.maps.osm.BitmapDescriptorFactory;
import com.car2go.maps.osm.CameraUpdateFactory;
import com.car2go.maps.osm.MapsConfiguration;

public class MainActivity extends AppCompatActivity {

	private MapContainerView mapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MapsConfiguration.getInstance().initialize(this);

		mapView = (MapContainerView) findViewById(R.id.map_view);

		mapView.onCreate(savedInstanceState);

		mapView.getMapAsync(new OnMapReadyCallback() {
			@Override
			public void onMapReady(AnyMap anyMap) {
				anyMap.moveCamera(
						CameraUpdateFactory.getInstance()
								.newLatLngZoom(
										new LatLng(53.5443465, 9.9289326),
										17f
								)
				);

				anyMap.addMarker(
						new MarkerOptions()
								.position(new LatLng(53.5443465, 9.9289326))
								.anchor(0.5f, 0.5f)
								.icon(
										BitmapDescriptorFactory.getInstance()
												.fromResource(R.drawable.marker)
								)
				);
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
