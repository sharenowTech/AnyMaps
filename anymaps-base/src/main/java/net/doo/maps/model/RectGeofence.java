package net.doo.maps.model;

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
        double x = latLng.longitude;
        double y = latLng.latitude;

        double minX = northWest.longitude;
        double maxX = southEast.longitude;

        double minY = southEast.latitude;
        double maxY = northWest.latitude;

        return latLng.latitude >= minY && y <= maxY && x >= minX && x <= maxX;
    }
}
