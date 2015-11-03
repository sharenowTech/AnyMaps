package com.daimler.car2go;

import net.doo.maps.model.LatLng;
import net.doo.maps.model.RectGeofence;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for the functionality of the RectGeofence
 */
@RunWith(JUnit4.class)
public class RectGeofenceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

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

    @Test
    public void testWrongRectEastWest() throws Exception {
        exception.expect(IllegalArgumentException.class);
        new RectGeofence(new LatLng(1, 1), new LatLng(-1, -1));
    }

    @Test
    public void testWrongRectNorthSouth() throws Exception {
        exception.expect(IllegalArgumentException.class);
        new RectGeofence(new LatLng(-1, -1), new LatLng(1, 1));
    }
}
