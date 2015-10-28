/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Region;

import net.doo.maps.model.LatLng;
import net.doo.maps.model.Polygon;
import net.doo.maps.model.PolygonOptions;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Overlay;

import java.util.ArrayList;
import java.util.List;

import static net.doo.maps.util.OsmUtils.toGeoPoints;

/**
 * Draws polygon on OpenStreetMaps.
 * Associated with given {@link net.doo.maps.AnyMap} object, so it should not be cached.
 */
public class OsmPolygon implements Polygon {

	private final MapView map;
	private final PolygonOverlay overlay;
	private final PolygonOptions options;

	OsmPolygon(MapView map, PolygonOptions options) {
		this.map = map;
		this.options = options;

		overlay = new PolygonOverlay(map, options);

		map.getOverlays().add(0, overlay);

		map.invalidate();
	}

	@Override
	public void setHoles(List<List<LatLng>> holes) {
		overlay.setHoles(holes);
	}

	@Override
	public List<LatLng> getPoints() {
		return options.getPoints();
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
	 * Overlay which draws a single polygon (with holes) on OpenStreetMap
	 */
	private static class PolygonOverlay extends Overlay {

		private final MapView map;

		private final boolean outsider;

		private final List<IGeoPoint> points;
		private final List<List<IGeoPoint>> holes = new ArrayList<>();

		private final Path polygonPath = new Path();
		private final Paint fillPaint;
		private final Paint strokePaint;

		private final Point auxPoint = new Point();

		public PolygonOverlay(MapView map, PolygonOptions options) {
			super(map.getContext());

			this.map = map;

			outsider = options.isOutsider();
			points = toGeoPoints(options.getPoints());

			fillPaint = new Paint();
			fillPaint.setStyle(Paint.Style.FILL);
			fillPaint.setColor(options.getFillColor());

			fillPaint.setAlpha(
					Color.alpha(options.getFillColor()) / 2
			);

			strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			strokePaint.setStyle(Paint.Style.STROKE);
			strokePaint.setColor(options.getStrokeColor());
			strokePaint.setStrokeWidth(options.getStrokeWidth());
		}

		void setHoles(List<List<LatLng>> holes) {
			this.holes.clear();

			for (List<LatLng> hole : holes) {
				this.holes.add(
						toGeoPoints(hole)
				);
			}

			map.invalidate();
		}

		@Override
		protected void draw(Canvas c, MapView osmv, boolean shadow) {
			Projection projection = osmv.getProjection();

			c.save();

			if (!holes.isEmpty()) {
				polygonPath.rewind();
				populateHoles(projection);

				// Draw outline of the holes
                c.drawPath(polygonPath, strokePaint);

				// Clip holes so that when we'll draw polygon on top of them the area under the
				// holes will be "cropped"
                c.clipPath(polygonPath, Region.Op.DIFFERENCE);
			}

			if (!points.isEmpty()) {
                if (!outsider) {
                    polygonPath.rewind();
                    populateOutline(projection);

	                // Draw and fill polygon itself
                    c.drawPath(polygonPath, fillPaint);
                    c.drawPath(polygonPath, strokePaint);
                } else {
	                // Optimization for outsider-polygons. Just fill the whole screen with the color.
	                // We cropped holes before, so it will appear as "transparent" polygons are
	                // drawn.
                    c.drawPaint(fillPaint);
                }
			}

			c.restore();
		}

		private void populateOutline(Projection projection) {
			populatePath(projection, points, polygonPath);
		}

		private void populateHoles(Projection projection) {
			for (List<IGeoPoint> hole : holes) {
				populatePath(projection, hole, polygonPath);
			}
		}

		private void populatePath(Projection projection, List<IGeoPoint> points, Path path) {
			Point startPoint = projection.toPixels(points.get(0), auxPoint);
			path.moveTo(startPoint.x, startPoint.y);

			for (int i = 1; i < points.size(); i++) {
				Point point = projection.toPixels(points.get(i), auxPoint);

				path.lineTo(point.x, point.y);
			}

			path.close();
		}

	}
}
