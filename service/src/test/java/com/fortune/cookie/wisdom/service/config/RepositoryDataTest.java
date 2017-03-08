package com.fortune.cookie.wisdom.service.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RepositoryDataTest {
	private static final String WISDOMS_BY_CATEGORY_AND_ID_URL = "http://repository/test/categories/{category}/{id}";
	private static final String WISDOMS_BY_CATEGORIES_URL = "http://repository/test/categories/{category}";
	private static final String CATEGORIES_URL = "http://repository/test/categories";

	private RepositoryData underTest;

	@Before
	public void setUp() {
		this.underTest = new RepositoryData();
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
		Assert.assertEquals(CATEGORIES_URL, actual);

	}

	@Test
	public void testGetWisdomsByCategoriesUriShouldReturnTheCorrectURI() {
		// GIVEN
		String category = "test_category";
		String expected = "http://repository/test/categories/" + category;
		// WHEN
		String actual = underTest.getWisdomsByCategoryURI(category).toString();
		// THEN
		Assert.assertEquals(expected, actual);

	}

	@Test
	public void testGetWisdomsByCategoryAndIdUriShouldReturnTheCorrectURI() {
		// GIVEN
		String category = "test_category";
		Long id = 1L;
		String expected = "http://repository/test/categories/" + category + "/" + id;
		// WHEN
		String actual = underTest.getWisdomByCategoryAndIdURI(category, 1L).toString();
		// THEN
		Assert.assertEquals(expected, actual);

	}
}
