/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.drawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import net.doo.maps.model.Circle;
import net.doo.maps.model.CircleOptions;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Overlay;

import static net.doo.maps.util.OsmUtils.toGeoPoint;

/**
 * Draws circle on OpenStreetMaps.
 * Associated with given {@link net.doo.maps.AnyMap} object, so it should not be cached.
 */
public class OsmCircle implements Circle {

	private final MapView map;
	private final CircleOverlay overlay;

	OsmCircle(MapView map, CircleOptions options) {
		this.map = map;

		overlay = new CircleOverlay(map.getContext(), options);
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
	 * Overlay which draws a single circle on OpenStreetMap
	 */
	private static class CircleOverlay extends Overlay {

		private final IGeoPoint position;
		private final float radius;

		private final Paint fillPaint;
		private final Paint strokePaint;

		private final Point auxPoint = new Point();

		public CircleOverlay(Context context, CircleOptions options) {
			super(context);

			position = toGeoPoint(options.getCenter());
			radius = (float) options.getRadius();

			fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			fillPaint.setStyle(Paint.Style.FILL);
			fillPaint.setColor(options.getFillColor());

			strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			strokePaint.setStyle(Paint.Style.STROKE);
			strokePaint.setColor(options.getStrokeColor());
			strokePaint.setStrokeWidth(options.getStrokeWidth());
		}

		@Override
		protected void draw(Canvas c, MapView osmv, boolean shadow) {
			Projection projection = osmv.getProjection();

			Point centerPoint = projection.toPixels(position, auxPoint);
			float radiusPixels = projection.metersToPixels(radius);

			c.drawCircle(
					centerPoint.x,
					centerPoint.y,
					radiusPixels,
					fillPaint
			);

			c.drawCircle(
					centerPoint.x,
					centerPoint.y,
					radiusPixels,
					strokePaint
			);
		}

	}
}
