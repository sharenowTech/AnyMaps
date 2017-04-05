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

/**
 * Utility functions that are used my both PolyUtil and SphericalUtil.
 */
class MathUtil {

	/**
	 * The earth's radius, in meters.
	 * Mean radius as defined by IUGG.
	 */
	static final double EARTH_RADIUS = 6371009.0D;

	MathUtil() {
	}

	/**
	 * Restrict x to the range [low, high].
	 */
	static double clamp(double x, double low, double high) {
		return x < low ? low : (x > high ? high : x);
	}

	/**
	 * Wraps the given value into the inclusive-exclusive interval between min and max.
	 *
	 * @param n   The value to wrap.
	 * @param min The minimum.
	 * @param max The maximum.
	 */
	static double wrap(double n, double min, double max) {
		return n >= min && n < max ? n : mod(n - min, max - min) + min;
	}

	/**
	 * Returns the non-negative remainder of x / m.
	 *
	 * @param x The operand.
	 * @param m The modulus.
	 */
	static double mod(double x, double m) {
		return (x % m + m) % m;
	}

	/**
	 * Returns mercator Y corresponding to latitude.
	 * See http://en.wikipedia.org/wiki/Mercator_projection .
	 */
	static double mercator(double lat) {
		return Math.log(Math.tan(lat * 0.5D + 0.7853981633974483D));
	}

	/**
	 * Returns latitude from mercator Y.
	 */
	static double inverseMercator(double y) {
		return 2.0D * Math.atan(Math.exp(y)) - 1.5707963267948966D;
	}

	/**
	 * Returns haversine(angle-in-radians).
	 * hav(x) == (1 - cos(x)) / 2 == sin(x / 2)^2.
	 */
	static double hav(double x) {
		double sinHalf = Math.sin(x * 0.5D);
		return sinHalf * sinHalf;
	}

	/**
	 * Computes inverse haversine. Has good numerical stability around 0.
	 * arcHav(x) == acos(1 - 2 * x) == 2 * asin(sqrt(x)).
	 * The argument must be in [0, 1], and the result is positive.
	 */
	static double arcHav(double x) {
		return 2.0D * Math.asin(Math.sqrt(x));
	}

	/**
	 * Given h==hav(x), returns sin(abs(x)).
	 */
	static double sinFromHav(double h) {
		return 2.0D * Math.sqrt(h * (1.0D - h));
	}

	/**
	 * Returns hav(asin(x)).
	 */
	static double havFromSin(double x) {
		double x2 = x * x;
		return x2 / (1.0D + Math.sqrt(1.0D - x2)) * 0.5D;
	}

	/**
	 * Returns sin(arcHav(x) + arcHav(y)).
	 */
	static double sinSumFromHav(double x, double y) {
		double a = Math.sqrt(x * (1.0D - x));
		double b = Math.sqrt(y * (1.0D - y));
		return 2.0D * (a + b - 2.0D * (a * y + b * x));
	}

	/**
	 * Returns hav() of distance from (lat1, lng1) to (lat2, lng2) on the unit sphere.
	 */
	static double havDistance(double lat1, double lat2, double dLng) {
		return hav(lat1 - lat2) + hav(dLng) * Math.cos(lat1) * Math.cos(lat2);
	}
}