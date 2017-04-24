/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package com.car2go.maps.google.adapter.factory.anymap;

import com.car2go.maps.google.adapter.UiSettingsAdapter;
import com.car2go.maps.google.adapter.factory.Mapper;
import com.google.android.gms.maps.UiSettings;

/**
 * Maps Google UiSettings to AnyMap UiSettings
 */
public class UiSettingsMapper implements Mapper<UiSettings, com.car2go.maps.UiSettings> {

	@Override
	public com.car2go.maps.UiSettings map(UiSettings input) {
		return new UiSettingsAdapter(input);
	}

}
