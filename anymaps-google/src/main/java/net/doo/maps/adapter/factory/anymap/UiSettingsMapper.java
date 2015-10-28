/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.adapter.factory.anymap;

import com.google.android.gms.maps.UiSettings;

import net.doo.maps.adapter.UiSettingsAdapter;
import net.doo.maps.adapter.factory.Mapper;

/**
 * Maps Google UiSettings to AnyMap UiSettings
 */
public class UiSettingsMapper implements Mapper<UiSettings, net.doo.maps.UiSettings> {

	@Override
	public net.doo.maps.UiSettings map(UiSettings input) {
		return new UiSettingsAdapter(input);
	}

}
