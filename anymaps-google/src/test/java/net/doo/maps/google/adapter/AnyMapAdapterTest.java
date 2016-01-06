/*
 * Copyright (c) 2015 Daimler AG / Moovel GmbH
 *
 * All rights reserved
 */

package net.doo.maps.google.adapter;

import net.doo.maps.google.adapter.factory.Mapper;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AnyMapAdapterTest {

	@Test
	public void testAdapt() throws Exception {
		// Given
		AnyMapAdapter adapter = new AnyMapAdapter();

		Mapper<Input, ?> mapper = mock(Mapper.class);

		adapter.registerMapper(Input.class, mapper);

		Input input = new Input();

		// When
		adapter.map(input);

		// Then
		verify(mapper).map(refEq(input));
	}

	@Test
	public void testAdaptList() throws Exception {
		// Given
		AnyMapAdapter adapter = new AnyMapAdapter();

		Mapper<Input, ?> mapper = mock(Mapper.class);

		adapter.registerMapper(Input.class, mapper);

		List<Input> inputList = asList(
				new Input(),
				new Input()
		);

		// When
		adapter.mapList(Input.class, inputList);

		// Then
		verify(mapper, times(inputList.size())).map(any(Input.class));
	}

	@Test(expected = IllegalStateException.class)
	public void testThrowIfNoMapperRegistered() throws Exception {
		// Given
		AnyMapAdapter adapter = new AnyMapAdapter();

		Input input = new Input();

		// When
		adapter.map(input);
	}

	@Test(expected = IllegalStateException.class)
	public void testThrowIfMapperRegisteredTwice() throws Exception {
		// Given
		AnyMapAdapter adapter = new AnyMapAdapter();

		Mapper<Input, ?> mapper = mock(Mapper.class);

		// When
		adapter.registerMapper(Input.class, mapper);
		adapter.registerMapper(Input.class, mapper);
	}

	private static class Input {
	}

}