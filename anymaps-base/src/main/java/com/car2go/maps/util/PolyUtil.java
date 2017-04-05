/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.car2go.maps.util;


import com.car2go.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PolyUtil {

	/**
	 * Decodes an encoded path string into a sequence of LatLngs.
	 */
	public static List<LatLng> decode(String encodedPath) {
		int len = encodedPath.length();
		ArrayList<LatLng> path = new ArrayList<>();
		int index = 0;
		int lat = 0;
		int lng = 0;

		while (index < len) {
			int result = 1;
			int shift = 0;

			int b;
			do {
				b = encodedPath.charAt(index++) - 63 - 1;
				result += b << shift;
				shift += 5;
			} while (b >= 31);

			lat += (result & 1) != 0 ? ~(result >> 1) : result >> 1;
			result = 1;
			shift = 0;

			do {
				b = encodedPath.charAt(index++) - 63 - 1;
				result += b << shift;
				shift += 5;
			} while (b >= 31);

			lng += (result & 1) != 0 ? ~(result >> 1) : result >> 1;
			path.add(new LatLng((double) lat * 1.0E-5D, (double) lng * 1.0E-5D));
		}

		return path;
	}

	/**
	 * Returns tan(latitude-at-lng3) on the great circle (lat1, lng1) to (lat2, lng2). lng1==0.
	 * See http://williams.best.vwh.net/avform.htm .
	 */
	private static double tanLatGC(double lat1, double lat2, double lng2, double lng3) {
		return (Math.tan(lat1) * Math.sin(lng2 - lng3) + Math.tan(lat2) * Math.sin(lng3)) / Math.sin(lng2);
	}

	/**
	 * Returns mercator(latitude-at-lng3) on the Rhumb line (lat1, lng1) to (lat2, lng2). lng1==0.
	 */
	private static double mercatorLatRhumb(double lat1, double lat2, double lng2, double lng3) {
		return (MathUtil.mercator(lat1) * (lng2 - lng3) + MathUtil.mercator(lat2) * lng3) / lng2;
	}

	/**
	 * Computes whether the vertical segment (lat3, lng3) to South Pole intersects the segment
	 * (lat1, lng1) to (lat2, lng2).
	 * Longitudes are offset by -lng1; the implicit lng1 becomes 0.
	 */
	private static boolean intersects(double lat1, double lat2, double lng2, double lat3, double lng3, boolean geodesic) {
		if ((lng3 < 0.0D || lng3 < lng2) && (lng3 >= 0.0D || lng3 >= lng2)) {
			if (lat3 <= -1.5707963267948966D) {
				return false;
			} else if (lat1 > -1.5707963267948966D && lat2 > -1.5707963267948966D && lat1 < 1.5707963267948966D && lat2 < 1.5707963267948966D) {
				if (lng2 <= -3.141592653589793D) {
					return false;
				} else {
					double linearLat = (lat1 * (lng2 - lng3) + lat2 * lng3) / lng2;
					return lat1 >= 0.0D && lat2 >= 0.0D && lat3 < linearLat ? false : (lat1 <= 0.0D && lat2 <= 0.0D && lat3 >= linearLat ? true : (lat3 >= 1.5707963267948966D ? true : (geodesic ? Math.tan(lat3) >= tanLatGC(lat1, lat2, lng2, lng3) : MathUtil.mercator(lat3) >= mercatorLatRhumb(lat1, lat2, lng2, lng3))));
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Computes whether the given point lies inside the specified polygon.
	 * The polygon is always cosidered closed, regardless of whether the last point equals
	 * the first or not.
	 * Inside is defined as not containing the South Pole -- the South Pole is always outside.
	 * The polygon is formed of great circle segments if geodesic is true, and of rhumb
	 * (loxodromic) segments otherwise.
	 */
	public static boolean containsLocation(LatLng point, List<LatLng> polygon, boolean geodesic) {
		int size = polygon.size();
		if (size == 0) {
			return false;
		} else {
			double lat3 = Math.toRadians(point.latitude);
			double lng3 = Math.toRadians(point.longitude);
			LatLng prev = polygon.get(size - 1);
			double lat1 = Math.toRadians(prev.latitude);
			double lng1 = Math.toRadians(prev.longitude);
			int nIntersect = 0;

			double lng2;
			for (Iterator i$ = polygon.iterator(); i$.hasNext(); lng1 = lng2) {
				LatLng point2 = (LatLng) i$.next();
				double dLng3 = MathUtil.wrap(lng3 - lng1, -3.141592653589793D, 3.141592653589793D);
				if (lat3 == lat1 && dLng3 == 0.0D) {
					return true;
				}

				double lat2 = Math.toRadians(point2.latitude);
				lng2 = Math.toRadians(point2.longitude);
				if (intersects(lat1, lat2, MathUtil.wrap(lng2 - lng1, -3.141592653589793D, 3.141592653589793D), lat3, dLng3, geodesic)) {
					++nIntersect;
				}

				lat1 = lat2;
			}

			return (nIntersect & 1) != 0;
		}

	}
}
