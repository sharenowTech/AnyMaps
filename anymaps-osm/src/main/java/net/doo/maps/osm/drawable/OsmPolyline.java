/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.osm.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import net.doo.maps.model.Polyline;
import net.doo.maps.model.PolylineOptions;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Overlay;

import java.util.List;

import static net.doo.maps.osm.util.OsmUtils.toGeoPoints;

/**
 * Draws polyline on OpenStreetMaps.
 * Associated with given {@link net.doo.maps.AnyMap} object, so it should not be cached.
 */
public class OsmPolyline implements Polyline {

	private final MapView map;
	private final PolylineOverlay overlay;

	public OsmPolyline(MapView map, PolylineOptions options) {
		this.map = map;

		overlay = new PolylineOverlay(map.getContext(), options);
		map.getOverlays().add(overlay);

		map.invalidate();
	}

	@Override
	public void setVisible(boolean visible) {
		overlay.setEnabled(visible);
		map.invalidate();
	}

	@Override
	public void remove() {
		map.getOverlays().remove(overlay);
		map.invalidate();
	}

	/**
	 * Overlay which draws a single polyline on OpenStreetMap
	 */
	private static class PolylineOverlay extends Overlay {

		private static final int ITEMS_PER_LINE = 4;

		private final List<IGeoPoint> geoPoints;
		private final float[] linePoints;

		private final Paint linePaint;

		private final Point auxPoint = new Point();

		public PolylineOverlay(Context ctx, PolylineOptions options) {
			super(ctx);

			geoPoints = toGeoPoints(options.getPoints());
			linePoints = new float[(geoPoints.size() - 1) * ITEMS_PER_LINE];

			linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			linePaint.setStyle(Paint.Style.STROKE);
			linePaint.setStrokeWidth(options.getWidth());
			linePaint.setStrokeCap(Paint.Cap.ROUND);
			linePaint.setColor(options.getColor());
		}

		@Override
		protected void draw(Canvas c, MapView osmv, boolean shadow) {
			Projection projection = osmv.getProjection();

			prepareLines(projection);

			c.drawLines(linePoints, linePaint);
		}

		private void prepareLines(Projection projection) {
			for (int i = 0; i < geoPoints.size() - 1; i++) {
				projection.toPixels(geoPoints.get(i), auxPoint);

				linePoints[ITEMS_PER_LINE * i] = auxPoint.x;
				linePoints[ITEMS_PER_LINE * i + 1] = auxPoint.y;

				projection.toPixels(geoPoints.get(i + 1), auxPoint);

				linePoints[ITEMS_PER_LINE * i + 2] = auxPoint.x;
				linePoints[ITEMS_PER_LINE * i + 3] = auxPoint.y;
			}
		}

	}

}
