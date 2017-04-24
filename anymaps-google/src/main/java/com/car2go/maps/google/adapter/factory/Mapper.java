/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory;

/**
 * Maps type I to type O
 */
public interface Mapper<I, O> {

	/**
	 * Maps type I to type O. Input must not be {@code null}.
	 */
	O map(I object);

}
