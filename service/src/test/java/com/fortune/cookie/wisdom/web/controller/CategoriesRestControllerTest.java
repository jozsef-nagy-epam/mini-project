package com.fortune.cookie.wisdom.web.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.fortune.cookie.wisdom.service.RestTemplateBasedWisdomSearchService;
import com.fortune.cookie.wisdom.service.WisdomSearchService;
import com.fortune.cookie.wisdom.web.domain.CategoryListResponse;

public class CategoriesRestControllerTest {
	private CategoriesRestController underTest;
	private WisdomSearchService service;

	@Before
	public void setUp() {
		service = Mockito.mock(RestTemplateBasedWisdomSearchService.class);
		underTest = new CategoriesRestController(service);
	}

	@Test
	public void testGetCategoriesShouldCallServiceAndTransformerAndLinkFactory() {
		// GIVEN
		String category = "Test_category";
		Set<String> categories = new HashSet<>(Arrays.asList(category));
		BDDMockito.given(service.getCategories()).willReturn(categories);
		// WHEN
		CategoryListResponse response = underTest.getCategories();
		// THEN
		assertThat("size of response list", response.getCategories().size() == 1);
		assertThat("response contains the correct category", response.getCategories().iterator().next().getCategory(),
				equalTo(category));
	}
}
