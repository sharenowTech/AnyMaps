/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;

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
import net.doo.maps.overlay.Converter;
import net.doo.maps.overlay.OutConverter;

/**
 * Implementation of {@link AnyMap} which works with Open Street Maps
 */
public class BaiduMap implements AnyMap {

	private final MapView mapView;

	BaiduMap(MapView mapView) {
		this.mapView = mapView;
		
		mapView.getMap().getUiSettings().setCompassEnabled(false);
		mapView.getMap().getUiSettings().setRotateGesturesEnabled(false);
	}

	@Override
	public void moveCamera(CameraUpdate cameraUpdate) {
		animateMapStatus((BaiduCameraUpdate) cameraUpdate, false, null);
	}

	@Override
	public void animateCamera(CameraUpdate cameraUpdate) {
		animateMapStatus((BaiduCameraUpdate) cameraUpdate, true, null);
	}

	@Override
	public void animateCamera(CameraUpdate cameraUpdate, CancelableCallback callback) {
		animateMapStatus((BaiduCameraUpdate) cameraUpdate, true, null);
		callback.onFinish();
	}

	@Override
	public void animateCamera(CameraUpdate cameraUpdate, int duration, CancelableCallback callback) {
		animateMapStatus((BaiduCameraUpdate) cameraUpdate, true, duration);
		callback.onFinish();
	}

	private void animateMapStatus(BaiduCameraUpdate baiduCameraUpdate, boolean animate, Integer duration) {
		MapStatusUpdate mapStatusUpdate;
		if (baiduCameraUpdate.bounds != null) {
			mapStatusUpdate = MapStatusUpdateFactory.newLatLngBounds(Converter.convert(baiduCameraUpdate.bounds));
		} else if (baiduCameraUpdate.center == null) {
			throw new IllegalArgumentException("We either need a center to zoom to or some bounds");
		} else if (baiduCameraUpdate.zoom != null) {
			mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(Converter.convert(baiduCameraUpdate.center), baiduCameraUpdate.zoom);
		} else {
			mapStatusUpdate = MapStatusUpdateFactory.newLatLng(Converter.convert(baiduCameraUpdate.center));
		}

		if (!animate) {
			mapView.getMap().setMapStatus(mapStatusUpdate);
		} else if (duration == null) {
			mapView.getMap().animateMapStatus(mapStatusUpdate);
		} else {
			mapView.getMap().animateMapStatus(mapStatusUpdate, duration);
		}
	}

	@Override
	public CameraPosition getCameraPosition() {
		return currentCameraPosition();
	}

	@Override
	public Projection getProjection() {
		return new BaiduProjection(
				new VisibleRegion(
						new LatLngBounds(
								OutConverter.convert(mapView.getMap().getMapStatus().bound.southwest),
								OutConverter.convert(mapView.getMap().getMapStatus().bound.northeast)
						)
				)
		);
	}

	@Override
	public Marker addMarker(MarkerOptions options) {
		com.baidu.mapapi.map.Marker marker = (com.baidu.mapapi.map.Marker) mapView.getMap().addOverlay(Converter.convert(options));
		return OutConverter.convert(marker);
	}

	@Override
	public Circle addCircle(CircleOptions options) {
		com.baidu.mapapi.map.Circle circle = (com.baidu.mapapi.map.Circle) mapView.getMap().addOverlay(Converter.convert(options));
		return OutConverter.convert(circle);
	}

	@Override
	public Polygon addPolygon(PolygonOptions options) {
		// TODO Make it a polygon again
		com.baidu.mapapi.map.Polyline polyline = (com.baidu.mapapi.map.Polyline) mapView.getMap().addOverlay(Converter.convertToPolyline(options));
		return OutConverter.convertToPolygon(mapView.getMap(), polyline);
//		com.baidu.mapapi.mapView.Polygon polygon = (com.baidu.mapapi.mapView.Polygon) mapView.getMap().addOverlay(Converterconvert(options));
//		return OutConverter.convert(polygon);
	}

	@Override
	public Polyline addPolyline(final PolylineOptions options) {
		com.baidu.mapapi.map.Polyline polyline = (com.baidu.mapapi.map.Polyline) mapView.getMap().addOverlay(Converter.convert(options));
		return OutConverter.convert(polyline);
	}

	@Override
	public UiSettings getUiSettings() {
		return new UiSettings() {
			@Override
			public void setAllGesturesEnabled(boolean enabled) {
				mapView.getMap().getUiSettings().setAllGesturesEnabled(enabled);
			}

			@Override
			public void setMyLocationButtonEnabled(boolean enabled) {
				mapView.getMap().setMyLocationEnabled(enabled);
			}

			@Override
			public void setMapToolbarEnabled(boolean enabled) {
				// Do nothing
			}
		};
	}

	@Override
	public void setOnMapClickListener(final OnMapClickListener listener) {
		mapView.getMap().setOnMapClickListener(new com.baidu.mapapi.map.BaiduMap.OnMapClickListener() {
			@Override
			public void onMapClick(com.baidu.mapapi.model.LatLng latLng) {
				listener.onMapClick(convert(latLng));
			}

			@Override
			public boolean onMapPoiClick(MapPoi mapPoi) {
				return false;
			}
		});
	}

	@Override
	public void setOnMapLongClickListener(final OnMapLongClickListener listener) {
		mapView.getMap().setOnMapLongClickListener(new com.baidu.mapapi.map.BaiduMap.OnMapLongClickListener() {
			@Override
			public void onMapLongClick(com.baidu.mapapi.model.LatLng latLng) {
				listener.onMapLongClick(convert(latLng));
			}
		});
	}

	private LatLng convert(com.baidu.mapapi.model.LatLng latLng) {
		return new LatLng(latLng.latitude, latLng.longitude);
	}

	@Override
	public void setOnCameraChangeListener(final OnCameraChangeListener listener) {
		mapView.getMap().setOnMapStatusChangeListener(new com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener() {
			@Override
			public void onMapStatusChangeStart(MapStatus mapStatus) {
				listener.onCameraChange(OutConverter.convert(mapStatus));
			}

			@Override
			public void onMapStatusChange(MapStatus mapStatus) {
				listener.onCameraChange(OutConverter.convert(mapStatus));
			}

			@Override
			public void onMapStatusChangeFinish(MapStatus mapStatus) {
				listener.onCameraChange(OutConverter.convert(mapStatus));
			}
		});
	}

	@Override
	public void setOnMarkerClickListener(final OnMarkerClickListener listener) {
		mapView.getMap().setOnMarkerClickListener(new com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener() {
			@Override
			public boolean onMarkerClick(com.baidu.mapapi.map.Marker marker) {
					listener.onMarkerClick(OutConverter.convert(marker));
					return true;
			}
		});
	}

	@Override
	public void setInfoWindowAdapter(InfoWindowAdapter adapter) {
		// Do nothing
	}

	@Override
	public void setTrafficEnabled(boolean enabled) {
		mapView.getMap().setTrafficEnabled(enabled);
	}

	@Override
	public void setMyLocationEnabled(boolean enabled) {
		mapView.getMap().setMyLocationEnabled(enabled);
	}

	@Override
	public void setMapType(Type type) {
		switch (type) {
			case SATELLITE:
				mapView.getMap().setMapType(com.baidu.mapapi.map.BaiduMap.MAP_TYPE_SATELLITE);
				break;
			case NORMAL:
			default:
				mapView.getMap().setMapType(com.baidu.mapapi.map.BaiduMap.MAP_TYPE_NORMAL);
				break;
		}
	}

	@Override
	public void setPadding(int left, int top, int right, int bottom) {
		mapView.setPadding(left, top, right, bottom);
	}

	/**
	 * @return native MapView reference
	 */
	MapView getNativeMapView() {
		return mapView;
	}

	private CameraPosition currentCameraPosition() {
		MapStatus mapStatus = mapView.getMap().getMapStatus();

		return new CameraPosition(
				new LatLng(mapStatus.target.latitude, mapStatus.target.longitude),
				mapStatus.zoom
		);
	}

	@Override
	public void onUserLocationChanged(LatLng location, float accuracy) {
		mapView.getMap().setMyLocationData(new MyLocationData.Builder().latitude(location.latitude).longitude(location.longitude).accuracy(accuracy).build());
	}

}
