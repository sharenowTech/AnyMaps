/*
 * Copyright (c) 2017 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps;

import android.view.View;

import com.car2go.maps.model.CameraPosition;
import com.car2go.maps.model.Circle;
import com.car2go.maps.model.CircleOptions;
import com.car2go.maps.model.LatLng;
import com.car2go.maps.model.Marker;
import com.car2go.maps.model.MarkerOptions;
import com.car2go.maps.model.Polygon;
import com.car2go.maps.model.PolygonOptions;
import com.car2go.maps.model.Polyline;
import com.car2go.maps.model.PolylineOptions;

/**
 * Provider-independent map controller. Originally was designed to mimic Google Map API and being
 * adapted to other providers. For detailed documentation on each method please refer Google Maps
 * documentation.
 */
public interface AnyMap {

	void moveCamera(CameraUpdate cameraUpdate);

	void animateCamera(CameraUpdate cameraUpdate);

	void animateCamera(CameraUpdate cameraUpdate, CancelableCallback callback);

	void animateCamera(CameraUpdate cameraUpdate, int duration, CancelableCallback callback);

	CameraPosition getCameraPosition();

	Projection getProjection();

	Marker addMarker(MarkerOptions options);

	Circle addCircle(CircleOptions options);

	Polygon addPolygon(PolygonOptions options);

	Polyline addPolyline(PolylineOptions options);

	UiSettings getUiSettings();

	void setOnMapClickListener(OnMapClickListener listener);

	void setOnMapLongClickListener(OnMapLongClickListener listener);

	void setOnCameraChangeListener(OnCameraChangeListener listener);

	void setOnMarkerClickListener(OnMarkerClickListener listener);

	void setInfoWindowAdapter(InfoWindowAdapter adapter);

	void setTrafficEnabled(boolean enabled);

	void setMyLocationEnabled(boolean enabled);

	void setMapType(Type type);

	void setPadding(int left, int top, int right, int bottom);

	void onUserLocationChanged(LatLng location, float accuracy);


	enum Type {

		NORMAL,
		SATELLITE

	}

	/**
	 * Features of {@link AnyMap} which might be supported or not supported
	 * by each particular implementation.
	 */
	enum Feature {

		/**
		 * Displaying layer with traffic jams on the map
		 */
		TRAFFIC_LAYER,

		/**
		 * Supporting several {@link com.car2go.maps.AnyMap.Type}. If this capability is not present,
		 * only one of types is implemented (which one - is not specified).
		 */
		MAP_TYPES,

		/**
		 * Supports being invisible at first and being revealed (or simply made visible) later on.
		 */
		REVEALABLE

	}

	interface OnMapClickListener {

		OnMapClickListener NULL = new OnMapClickListener() {
			@Override
			public void onMapClick(LatLng latLng) {
				// Do nothing
			}
		};

		void onMapClick(LatLng latLng);

	}

	interface OnMapLongClickListener {

		OnMapLongClickListener NULL = new OnMapLongClickListener() {
			@Override
			public void onMapLongClick(LatLng latLng) {
				// Do nothing
			}
		};

		void onMapLongClick(LatLng latLng);

	}

	interface OnCameraChangeListener {

		void onCameraChange(CameraPosition cameraPosition);

	}

	interface OnMarkerClickListener {

		OnMarkerClickListener NULL = new OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(Marker marker) {
				// Do nothing
				return false;
			}
		};

		boolean onMarkerClick(Marker marker);

	}

	interface CancelableCallback {

		void onFinish();

		void onCancel();

	}

	interface InfoWindowAdapter {

		View getInfoWindow(Marker marker);

		View getInfoContents(Marker marker);

	}

}
