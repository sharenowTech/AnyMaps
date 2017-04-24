/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter;

import android.support.annotation.NonNull;

import com.car2go.maps.google.adapter.factory.Mapper;
import com.car2go.maps.google.adapter.factory.anymap.CameraPositionMapper;
import com.car2go.maps.google.adapter.factory.anymap.CircleMapper;
import com.car2go.maps.google.adapter.factory.anymap.LatLngBoundsMapper;
import com.car2go.maps.google.adapter.factory.anymap.LatLngMapper;
import com.car2go.maps.google.adapter.factory.anymap.MarkerMapper;
import com.car2go.maps.google.adapter.factory.anymap.PolygonMapper;
import com.car2go.maps.google.adapter.factory.anymap.PolylineMapper;
import com.car2go.maps.google.adapter.factory.anymap.ProjectionMapper;
import com.car2go.maps.google.adapter.factory.anymap.UiSettingsMapper;
import com.car2go.maps.google.adapter.factory.anymap.VisibleRegionMapper;
import com.car2go.maps.google.adapter.factory.google.CircleOptionsMapper;
import com.car2go.maps.google.adapter.factory.google.MarkerOptionsMapper;
import com.car2go.maps.google.adapter.factory.google.PolygonOptionsMapper;
import com.car2go.maps.google.adapter.factory.google.PolylineOptionsMapper;
import com.car2go.maps.model.CircleOptions;
import com.car2go.maps.model.MarkerOptions;
import com.car2go.maps.model.PolygonOptions;
import com.car2go.maps.model.PolylineOptions;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.VisibleRegion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Utility for adapting Google entities to AnyMap and vice versa.
 */
public class AnyMapAdapter {

	private static final AnyMapAdapter INSTANCE = new AnyMapAdapter();
	private final HashMap<Class<?>, Mapper> mappers = new HashMap<>();

	AnyMapAdapter() {
		registerGoogleToAnyMapMappers();
		registerAnyMapToGoogleMappers();
	}

	/**
	 * @return instance of {@link AnyMapAdapter}
	 */
	private static AnyMapAdapter getInstance() {
		return INSTANCE;
	}

	/**
	 * Adapts input object to it's respective alternative. That is, adapts Google entities to
	 * AnyMap entities and vice versa. If input is {@code null}, outputs {@code null}.
	 *
	 * @param object object to adapt
	 * @return adapted object
	 */
	public static <I, O> O adapt(I object) {
		return getInstance().map(object);
	}

	/**
	 * Convenience for {@link #adapt(Object)} which works on a {@link List} of objects.
	 *
	 * @param type type of input objects being adapted
	 * @param list list of objects being adapted
	 * @return list of adapted objects
	 */
	public static <I, O> List<O> adaptList(Class<? extends I> type, List<I> list) {
		return getInstance().mapList(type, list);
	}

	private void registerGoogleToAnyMapMappers() {
		registerMapper(
				LatLng.class,
				new LatLngMapper()
		);
		registerMapper(
				LatLngBounds.class,
				new LatLngBoundsMapper()
		);
		registerMapper(
				CameraPosition.class,
				new CameraPositionMapper()
		);
		registerMapper(
				Projection.class,
				new ProjectionMapper()
		);
		registerMapper(
				VisibleRegion.class,
				new VisibleRegionMapper()
		);
		registerMapper(
				UiSettings.class,
				new UiSettingsMapper()
		);
		registerMapper(
				Marker.class,
				new MarkerMapper()
		);
		registerMapper(
				Circle.class,
				new CircleMapper()
		);
		registerMapper(
				Polygon.class,
				new PolygonMapper()
		);
		registerMapper(
				Polyline.class,
				new PolylineMapper()
		);
	}

	private void registerAnyMapToGoogleMappers() {
		registerMapper(
				com.car2go.maps.model.LatLng.class,
				new com.car2go.maps.google.adapter.factory.google.LatLngMapper()
		);
		registerMapper(
				com.car2go.maps.model.LatLngBounds.class,
				new com.car2go.maps.google.adapter.factory.google.LatLngBoundsMapper()
		);
		registerMapper(
				MarkerOptions.class,
				new MarkerOptionsMapper()
		);
		registerMapper(
				CircleOptions.class,
				new CircleOptionsMapper()
		);
		registerMapper(
				PolygonOptions.class,
				new PolygonOptionsMapper()
		);
		registerMapper(
				PolylineOptions.class,
				new PolylineOptionsMapper()
		);
	}

	/**
	 * Registers mapper which will map instances of input classes into some other type. Output
	 * type is defined by mapper.
	 */
	public <I> void registerMapper(Class<? extends I> inputClass, Mapper<I, ?> mapper) {
		if (mappers.containsKey(inputClass)) {
			throw new IllegalStateException(
					"Mapper for class " + inputClass
							+ " was already registered: " + mappers.get(inputClass)
			);
		}

		mappers.put(inputClass, mapper);
	}

	/**
	 * Adapts input object to it's respective alternative. That is, adapts Google entities to
	 * AnyMap entities and vice versa. If input is {@code null}, outputs {@code null}.
	 *
	 * @param object object to adapt
	 * @return adapted object
	 */
	public <I, O> O map(I object) {
		if (object == null) {
			return null;
		}

		Mapper<I, O> mapper = findMapper(object.getClass());

		return mapper.map(object);
	}

	/**
	 * Convenience for {@link #map(Object)} which works on a {@link List} of objects.
	 *
	 * @param type type of input objects being adapted
	 * @param input list of objects being adapted
	 * @return list of adapted objects
	 */
	public <I, O> List<O> mapList(Class<? extends I> type, List<I> input) {
		Mapper<I, O> mapper = findMapper(type);

		List<O> result = new ArrayList<>(input.size());

		for (I inputItem : input) {
			result.add(
					mapper.map(inputItem)
			);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	@NonNull
	private <I, O> Mapper<I, O> findMapper(Class<?> type) {
		// We have to do unchecked cast since we don't know the exact type of adapter in our map
		Mapper<I, O> mapper = mappers.get(type);
		if (mapper == null) {
			throw new IllegalStateException("No mapper for " + type);
		}
		return mapper;
	}

}
