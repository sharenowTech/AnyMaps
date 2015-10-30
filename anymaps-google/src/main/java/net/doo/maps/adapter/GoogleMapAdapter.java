/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter;

import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

import net.doo.maps.AnyMap;
import net.doo.maps.CameraUpdate;
import net.doo.maps.Projection;
import net.doo.maps.UiSettings;
import net.doo.maps.model.CameraPosition;
import net.doo.maps.model.Circle;
import net.doo.maps.model.CircleOptions;
import net.doo.maps.model.Marker;
import net.doo.maps.model.MarkerOptions;
import net.doo.maps.model.Polygon;
import net.doo.maps.model.PolygonOptions;
import net.doo.maps.model.Polyline;
import net.doo.maps.model.PolylineOptions;

/**
 * Implementation of {@link AnyMap} which works with {@link com.google.android.gms.maps.GoogleMap}
 */
public class GoogleMapAdapter implements AnyMap {

    private final GoogleMap map;
    private final DrawableComponentFactory drawableComponentFactory;

    public GoogleMapAdapter(GoogleMap map) {
        this.map = map;

        drawableComponentFactory = new DrawableComponentFactory(map);
    }

    @Override
    public void moveCamera(CameraUpdate cameraUpdate) {
        map.moveCamera(
                ((CameraUpdateAdapter) cameraUpdate).wrappedCameraUpdate
        );
    }

    @Override
    public void animateCamera(CameraUpdate cameraUpdate) {
        map.animateCamera(
                ((CameraUpdateAdapter) cameraUpdate).wrappedCameraUpdate
        );
    }

    @Override
    public void animateCamera(CameraUpdate cameraUpdate, CancelableCallback callback) {
        map.animateCamera(
                ((CameraUpdateAdapter) cameraUpdate).wrappedCameraUpdate,
                new CancellableCallbackAdapter(callback)
        );
    }

    @Override
    public void animateCamera(CameraUpdate cameraUpdate, int duration, final CancelableCallback callback) {
        map.animateCamera(
                ((CameraUpdateAdapter) cameraUpdate).wrappedCameraUpdate,
                duration,
                new CancellableCallbackAdapter(callback)
        );
    }

    @Override
    public CameraPosition getCameraPosition() {
        return AnyMapAdapter.adapt(map.getCameraPosition());
    }

    @Override
    public Projection getProjection() {
        return AnyMapAdapter.adapt(map.getProjection());
    }

    @Override
    public Marker addMarker(MarkerOptions options) {
        return drawableComponentFactory.addMarker(options);
    }

    @Override
    public Circle addCircle(CircleOptions options) {
        return drawableComponentFactory.addCircle(options);
    }

    @Override
    public Polygon addPolygon(PolygonOptions options) {
        return drawableComponentFactory.addPolygon(options);
    }

    @Override
    public Polyline addPolyline(PolylineOptions options) {
        return drawableComponentFactory.addPolyline(options);
    }

    @Override
    public UiSettings getUiSettings() {
        return AnyMapAdapter.adapt(map.getUiSettings());
    }

    @Override
    public void setOnMapClickListener(final OnMapClickListener listener) {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                net.doo.maps.model.LatLng anyLatLng = AnyMapAdapter.adapt(latLng);

                listener.onMapClick(anyLatLng);
            }
        });
    }

    @Override
    public void setOnMapLongClickListener(final OnMapLongClickListener listener) {
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                net.doo.maps.model.LatLng anyLatLng = AnyMapAdapter.adapt(latLng);

                listener.onMapLongClick(anyLatLng);
            }
        });
    }

    @Override
    public void setOnCameraChangeListener(final OnCameraChangeListener listener) {
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(com.google.android.gms.maps.model.CameraPosition cameraPosition) {
                CameraPosition anyCameraPosition = AnyMapAdapter.adapt(cameraPosition);

                listener.onCameraChange(anyCameraPosition);
            }
        });
    }

    @Override
    public void setOnMarkerClickListener(final OnMarkerClickListener listener) {
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {
                Marker anyMarker = AnyMapAdapter.adapt(marker);

                return listener.onMarkerClick(anyMarker);
            }
        });
    }

    @Override
    public void setInfoWindowAdapter(final InfoWindowAdapter adapter) {
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(com.google.android.gms.maps.model.Marker marker) {
                Marker anyMarker = AnyMapAdapter.adapt(marker);

                return adapter.getInfoWindow(anyMarker);
            }

            @Override
            public View getInfoContents(com.google.android.gms.maps.model.Marker marker) {
                Marker anyMarker = AnyMapAdapter.adapt(marker);

                return adapter.getInfoContents(anyMarker);
            }
        });
    }

    @Override
    public void setTrafficEnabled(boolean enabled) {
        map.setTrafficEnabled(enabled);
    }

    @Override
    public void setMyLocationEnabled(boolean enabled) {
        map.setMyLocationEnabled(enabled);
    }

    @Override
    public void setMapType(Type type) {
        int googleMapType;

        switch (type) {
            case SATELLITE:
                googleMapType = GoogleMap.MAP_TYPE_SATELLITE;
                break;
            case NORMAL:
            default:
                googleMapType = GoogleMap.MAP_TYPE_NORMAL;
                break;
        }

        map.setMapType(googleMapType);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        map.setPadding(left, top, right, bottom);
    }

    @Override
    public void onUserLocationChanged(net.doo.maps.model.LatLng location, float accuracy) {
        //Do nothing
    }

    /**
     * Delegates callbacks from Google map to given AnyMap callback
     */
    private static class CancellableCallbackAdapter implements GoogleMap.CancelableCallback {

        private final CancelableCallback callback;

        public CancellableCallbackAdapter(CancelableCallback callback) {
            this.callback = callback;
        }

        @Override
        public void onFinish() {
            callback.onFinish();
        }

        @Override
        public void onCancel() {
            callback.onCancel();
        }

    }
}
