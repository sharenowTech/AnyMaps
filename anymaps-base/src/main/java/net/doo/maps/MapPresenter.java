/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps;

import android.os.Bundle;

/**
 * Mimics Google MapView
 */
public interface MapPresenter {

	AnyMap getMap();

	void getMapAsync(OnMapReadyCallback callback);

	void onCreate(Bundle savedInstanceState);

	void onResume();

	void onPause();

	void onDestroy();

	void onLowMemory();

	void onSaveInstanceState(Bundle outState);


}
