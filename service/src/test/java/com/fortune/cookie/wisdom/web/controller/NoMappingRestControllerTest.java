package com.fortune.cookie.wisdom.web.controller;

import org.junit.Before;
import org.junit.Test;

import com.fortune.cookie.wisdom.web.domain.exception.HandlerNotFoundException;

public class NoMappingRestControllerTest {

	private NoMappingRestController underTest;

	@Before
	public void setUps() {
		underTest = new NoMappingRestController();
	}

	@Test(expected = HandlerNotFoundException.class)
	public void noHandlerMappingFoundShouldThrowException() {
		// GIVEN
		// WHEN
		underTest.noHandlerMappingFound();
		// THEN exception should be thrown
	}
}
