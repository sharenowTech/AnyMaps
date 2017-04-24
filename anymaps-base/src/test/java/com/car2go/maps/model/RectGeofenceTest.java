package com.car2go.maps.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the functionality of the RectGeofence
 */
@RunWith(JUnit4.class)
public class RectGeofenceTest {

    private RectGeofence createFence() {
        return new RectGeofence(new LatLng(1, -1), new LatLng(-1, 1));
    }

    @Test
    public void testInside() throws Exception {
        assertTrue(createFence().contains(new LatLng(0, 0)));
        assertTrue(createFence().contains(new LatLng(0.999, 0.999)));
        assertTrue(createFence().contains(new LatLng(-0.999, 0.999)));
        assertTrue(createFence().contains(new LatLng(-0.999, -0.999)));
        assertTrue(createFence().contains(new LatLng(0.999, -0.999)));
    }

    @Test
    public void testOutside() throws Exception {
        assertFalse(createFence().contains(new LatLng(1.001, 0)));
        assertFalse(createFence().contains(new LatLng(-1.001, 0)));
        assertFalse(createFence().contains(new LatLng(0, 1.001)));
        assertFalse(createFence().contains(new LatLng(0, -1.001)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongRectEastWest() throws Exception {
        new RectGeofence(new LatLng(1, 1), new LatLng(-1, -1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWrongRectNorthSouth() throws Exception {
        new RectGeofence(new LatLng(-1, -1), new LatLng(1, 1));
    }
}
