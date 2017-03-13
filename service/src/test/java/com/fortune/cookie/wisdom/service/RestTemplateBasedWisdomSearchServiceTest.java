package com.fortune.cookie.wisdom.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fortune.cookie.wisdom.service.config.URLBuilder;
import com.fortune.cookie.wisdom.service.domain.CategoryList;
import com.fortune.cookie.wisdom.service.domain.Wisdom;
import com.fortune.cookie.wisdom.service.domain.WisdomList;

public class RestTemplateBasedWisdomSearchServiceTest {
	private RestTemplateBasedWisdomSearchService underTest;
	private URLBuilder urlBuilder;
	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		urlBuilder = Mockito.mock(URLBuilder.class);
		restTemplate = Mockito.mock(RestTemplate.class);
		underTest = new RestTemplateBasedWisdomSearchService(urlBuilder, restTemplate);
	}

	@Test
	public void testGetCategoriesShouldCallRepoApiAndtransformResponse() throws URISyntaxException {
		// GIVEN
		URI uri = new URI("");
		CategoryList categories = new CategoryList();
		ResponseEntity<CategoryList> response = new ResponseEntity<>(categories, HttpStatus.OK);
		BDDMockito.given(urlBuilder.getCategoriesURI()).willReturn(uri);
		BDDMockito.given(restTemplate.getForEntity(uri, CategoryList.class)).willReturn(response);
		// WHEN
		underTest.getCategories();
		// THEN
		BDDMockito.then(urlBuilder).should(BDDMockito.times(1)).getCategoriesURI();
		BDDMockito.then(restTemplate).should(BDDMockito.times(1)).getForEntity(uri, CategoryList.class);
	}

	@Test
	public void testGetWisdomsByCategoryShouldCallRepoApiAndtransformResponse() throws URISyntaxException {
		// GIVEN
		String category = "test_category";
		URI uri = new URI("");
		WisdomList wisdoms = new WisdomList();
		ResponseEntity<WisdomList> response = new ResponseEntity<>(wisdoms, HttpStatus.OK);
		BDDMockito.given(urlBuilder.getWisdomsByCategoryURI(category)).willReturn(uri);
		BDDMockito.given(restTemplate.getForEntity(uri, WisdomList.class)).willReturn(response);
		// WHEN
		underTest.getWisdomsByCategory(category);
		// THEN
		BDDMockito.then(urlBuilder).should(BDDMockito.times(1)).getWisdomsByCategoryURI(category);
		BDDMockito.then(restTemplate).should(BDDMockito.times(1)).getForEntity(uri, WisdomList.class);
	}

	@Test
	public void testGetWisdomsByCategoriesAndIdShouldCallRepoApiAndtransformResponse() throws URISyntaxException {
		// GIVEN
		String category = "test_category";
		Long wisdomId = 1L;
		URI uri = new URI("");
		Wisdom wisdom = new Wisdom();
		ResponseEntity<Wisdom> response = new ResponseEntity<>(wisdom, HttpStatus.OK);
		BDDMockito.given(urlBuilder.getWisdomByCategoryAndIdURI(category, wisdomId)).willReturn(uri);
		BDDMockito.given(restTemplate.getForEntity(uri, Wisdom.class)).willReturn(response);
		// WHEN
		underTest.getWisdomByCategoryAndId(category, wisdomId);
		// THEN
		BDDMockito.then(urlBuilder).should(BDDMockito.times(1)).getWisdomByCategoryAndIdURI(category, wisdomId);
		BDDMockito.then(restTemplate).should(BDDMockito.times(1)).getForEntity(uri, Wisdom.class);
	}
}
