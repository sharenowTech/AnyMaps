package com.car2go.maps.model;

/**
 * Defines handling of geofences
 */
public interface Geofence {

    /**
     * Checks if a location is inside the geofence or not
     * @param latLng location to change
     * @return {@code true} if location is inside the geofence
     */
    public boolean contains(LatLng latLng);
}
