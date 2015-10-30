/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;

import net.doo.maps.adapter.GoogleMapAdapter;
import net.doo.maps.google.R;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.EnumSet.of;

/**
 * @see MapPresenter
 */
public class MapView extends MapContainerView {

    private com.google.android.gms.maps.MapView wrappedMapView;

    private AnyMap map;

    /**
     * Provides capabilities of the {@link AnyMap} implementation. If some features are not supported
     * and you will try to call them - nothing will happen.
     *
     * @return capabilities of the {@link AnyMap} implementation.
     */
    public static Set<AnyMap.Capability> getCapabilities() {
        return unmodifiableSet(of(AnyMap.Capability.MAP_TYPES, AnyMap.Capability.TRAFFIC_LAYER, AnyMap.Capability.REVEALABLE));
    }

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

        wrappedMapView = new com.google.android.gms.maps.MapView(context, googleMapOptions);

        addView(wrappedMapView);
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
    public AnyMap getMap() {
        if (map == null) {
            map = new GoogleMapAdapter(wrappedMapView.getMap());
        }

        return map;
    }

    @Override
    public void getMapAsync(final OnMapReadyCallback callback) {
        if (map != null) {
            callback.onMapReady(map);
            return;
        }

        wrappedMapView.getMapAsync(new com.google.android.gms.maps.OnMapReadyCallback() {
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
        wrappedMapView.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        wrappedMapView.onResume();
    }

    @Override
    public void onPause() {
        wrappedMapView.onPause();
    }

    @Override
    public void onDestroy() {
        if (map != null) {
            map.setMyLocationEnabled(false);
        }

        wrappedMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        wrappedMapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        wrappedMapView.onSaveInstanceState(outState);
    }

}
