package com.car2go.maps.model;

/**
 * A rectangular geofence composed of two locations.
 *
 * The locations should be the North-West corner and the South-East corner of the rect.
 */
public class RectGeofence implements Geofence {
    private final LatLng northWest;
    private final LatLng southEast;

    public RectGeofence(LatLng northWest, LatLng southEast) {
        this.northWest = northWest;
        this.southEast = southEast;

        if ((northWest.latitude <= southEast.latitude) || (northWest.longitude >= southEast.longitude)) {
            throw new IllegalArgumentException("North West point should be in the Top Left corner of the rect");
        }
    }

    @Override
    public boolean contains(LatLng latLng) {
        double longitude = latLng.longitude;
        double latitude = latLng.latitude;

        double leftBorder = northWest.longitude;
        double rightBorder = southEast.longitude;

        double bottomBorder = southEast.latitude;
        double topBorder = northWest.latitude;

        return latitude >= bottomBorder && latitude <= topBorder && longitude >= leftBorder && longitude <= rightBorder;
    }
}
