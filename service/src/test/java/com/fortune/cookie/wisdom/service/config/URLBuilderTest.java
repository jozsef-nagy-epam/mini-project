package com.fortune.cookie.wisdom.service.config;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.fortune.cookie.wisdom.service.config.URLBuilder;

public class URLBuilderTest {
	private static final String ROOT_URL = "http://repository/";
	private static final String WISDOMS_BY_CATEGORY_AND_ID_URL = "test/categories/{category}/{id}";
	private static final String WISDOMS_BY_CATEGORIES_URL = "test/categories/{category}";
	private static final String CATEGORIES_URL = "test/categories";

	private URLBuilder underTest;

	@Before
	public void setUp() {
		this.underTest = new URLBuilder();
		underTest.setRootUrl(ROOT_URL);
		underTest.setCategories(CATEGORIES_URL);
		underTest.setWisdomsByCategory(WISDOMS_BY_CATEGORIES_URL);
		underTest.setWisdomsByCategoryAndId(WISDOMS_BY_CATEGORY_AND_ID_URL);
	}

	@Test
	public void testGetCategoriesUriShouldReturnTheCorrectURI() {
		// GIVEN in setUp

		// WHEN
		String actual = underTest.getCategories().toString();
		// THEN
		assertThat("Categories url is correct", actual, equalTo(CATEGORIES_URL));

	}

	@Test
	public void testGetWisdomsByCategoriesUriShouldReturnTheCorrectURI() {
		// GIVEN
		String category = "test_category";
		String expected = "http://repository/test/categories/" + category;
		// WHEN
		String actual = underTest.getWisdomsByCategoryURI(category).toString();
		// THEN
		assertThat("WisdomsByCategories url is correct", actual, equalTo(expected));

	}

	@Test
	public void testGetWisdomByCategoryAndIdUriShouldReturnTheCorrectURI() {
		// GIVEN
		String category = "test_category";
		Long id = 1L;
		String expected = "http://repository/test/categories/" + category + "/" + id;
		// WHEN
		String actual = underTest.getWisdomByCategoryAndIdURI(category, 1L).toString();
		// THEN
		assertThat("WisdomByCategoryAndId url is correct", actual, equalTo(expected));

	}
}
