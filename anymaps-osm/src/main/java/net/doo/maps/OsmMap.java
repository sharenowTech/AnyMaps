/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import net.doo.maps.drawable.DrawableComponentFactory;
import net.doo.maps.model.CameraPosition;
import net.doo.maps.model.Circle;
import net.doo.maps.model.CircleOptions;
import net.doo.maps.model.LatLng;
import net.doo.maps.model.LatLngBounds;
import net.doo.maps.model.Marker;
import net.doo.maps.model.MarkerOptions;
import net.doo.maps.model.Polygon;
import net.doo.maps.model.PolygonOptions;
import net.doo.maps.model.Polyline;
import net.doo.maps.model.PolylineOptions;
import net.doo.maps.model.VisibleRegion;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.events.MapListener;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.views.MapView;

import static net.doo.maps.util.OsmUtils.toLatLng;

/**
 * Implementation of {@link AnyMap} which works with Open Street Maps
 */
class OsmMap implements AnyMap {

    private final org.osmdroid.views.MapView map;

    private final CameraUpdateHandler cameraUpdateHandler;
    private final MyLocationHandler myLocationHandler;
    private final DrawableComponentFactory drawableComponentFactory;
    private final UiSettings uiSettings;

    private OnMapClickListener onMapClickListener = OnMapClickListener.NULL;
    private OnMapLongClickListener onMapLongClickListener = OnMapLongClickListener.NULL;

    private boolean mapEnabled = true;

    OsmMap(MapView map) {
        this.map = map;

        cameraUpdateHandler = new CameraUpdateHandler(map);
        myLocationHandler = new MyLocationHandler(map);
        drawableComponentFactory = new DrawableComponentFactory(map);
        uiSettings = new OsmUiSettings();

        map.setOnTouchListener(new MapTouchListener(map.getContext()));
    }

    @Override
    public void moveCamera(CameraUpdate cameraUpdate) {
        cameraUpdateHandler.moveCamera(cameraUpdate);
    }

    @Override
    public void animateCamera(CameraUpdate cameraUpdate) {
        cameraUpdateHandler.animateCamera(cameraUpdate);
    }

    @Override
    public void animateCamera(CameraUpdate cameraUpdate, CancelableCallback callback) {
        cameraUpdateHandler.animateCamera(cameraUpdate, callback);
    }

    @Override
    public void animateCamera(CameraUpdate cameraUpdate, int duration, CancelableCallback callback) {
        cameraUpdateHandler.animateCamera(cameraUpdate, duration, callback);
    }

    @Override
    public CameraPosition getCameraPosition() {
        return currentCameraPosition();
    }

    @Override
    public Projection getProjection() {
        org.osmdroid.views.Projection projection = map.getProjection();

        return new OsmProjection(
                new VisibleRegion(
                        new LatLngBounds(
                                new LatLng(
                                        projection.getSouthWest().getLatitude(),
                                        projection.getSouthWest().getLongitude()
                                ),
                                new LatLng(
                                        projection.getNorthEast().getLatitude(),
                                        projection.getNorthEast().getLongitude()
                                )
                        )
                )
        );
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
        return uiSettings;
    }

    @Override
    public void setOnMapClickListener(OnMapClickListener listener) {
        onMapClickListener = (listener == null)
                ? OnMapClickListener.NULL
                : listener;
    }

    @Override
    public void setOnMapLongClickListener(OnMapLongClickListener listener) {
        onMapLongClickListener = (listener == null)
                ? OnMapLongClickListener.NULL
                : listener;
    }

    @Override
    public void setOnCameraChangeListener(OnCameraChangeListener listener) {
        map.setMapListener(new OsmMapListener(listener));
    }

    @Override
    public void setOnMarkerClickListener(OnMarkerClickListener listener) {
        drawableComponentFactory.setOnMarkerClickListener(listener);
    }

    @Override
    public void setInfoWindowAdapter(InfoWindowAdapter adapter) {
        // Do nothing
    }

    @Override
    public void setTrafficEnabled(boolean enabled) {
        // Do nothing
    }

    @Override
    public void setMyLocationEnabled(boolean enabled) {
        myLocationHandler.setMyLocationEnabled(enabled);
    }

    @Override
    public void setMapType(Type type) {
        // Do nothing
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        map.setTranslationY((top - bottom) / 2f);
        map.setTranslationX((left - right) / 2f);
    }

    @Override
    public void onUserLocationChanged(LatLng location, float accuracy) {
        //Do nothing
    }

    private CameraPosition currentCameraPosition() {
        final IGeoPoint center = map.getMapCenter();
        final int zoomLevel = map.getZoomLevel();

        return new CameraPosition(
                new LatLng(center.getLatitude(), center.getLongitude()),
                zoomLevel
        );
    }

    /**
     * Detects clicks and long-clicks on map
     */
    private class MapTouchListener implements View.OnTouchListener, GestureDetector.OnGestureListener {

        private GestureDetector gestureDetector;

        public MapTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, this);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (!mapEnabled) {
                return true;
            }

            gestureDetector.onTouchEvent(event);

            return false;
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            onMapClickListener.onMapClick(touchPoint(e));

            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            onMapLongClickListener.onMapLongClick(touchPoint(e));
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }

        private LatLng touchPoint(MotionEvent e) {
            IGeoPoint geoPoint = map.getProjection().fromPixels(
                    (int) e.getX(),
                    (int) e.getY()
            );
            return toLatLng(geoPoint);
        }

    }

    /**
     * Listens for map position changes and delegates them
     * to {@link net.doo.maps.AnyMap.OnCameraChangeListener}
     */
    private class OsmMapListener implements MapListener {

        private final OnCameraChangeListener listener;

        private OsmMapListener(OnCameraChangeListener listener) {
            this.listener = listener;
        }

        @Override
        public boolean onScroll(ScrollEvent event) {
            notifyListener();
            return false;
        }

        @Override
        public boolean onZoom(ZoomEvent event) {
            notifyListener();
            return false;
        }

        private void notifyListener() {
            listener.onCameraChange(
                    currentCameraPosition()
            );
        }

    }

    /**
     * UI settings for OpenStreetMap
     */
    private class OsmUiSettings implements UiSettings {

        @Override
        public void setAllGesturesEnabled(final boolean enabled) {
            mapEnabled = enabled;
        }

        @Override
        public void setMyLocationButtonEnabled(boolean enabled) {
            // Do nothing
        }

        @Override
        public void setMapToolbarEnabled(boolean enabled) {
            // Do nothing
        }
    }

}
