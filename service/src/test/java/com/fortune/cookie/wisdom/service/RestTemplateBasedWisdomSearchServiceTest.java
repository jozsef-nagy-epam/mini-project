package com.fortune.cookie.wisdom.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fortune.cookie.wisdom.config.RepositoryData;
import com.fortune.cookie.wisdom.service.domain.Wisdom;
import com.fortune.cookie.wisdom.service.transformer.ResponseToPojoTransformer;

public class RestTemplateBasedWisdomSearchServiceTest {
	@InjectMocks
	private RestTemplateBasedWisdomSearchService underTest;
	@Mock
	private RepositoryData repoData;
	@Mock
	private RestTemplate restTemplate;
	@Mock
	private ResponseToPojoTransformer transformer;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetCategoriesShouldCallRepoApiAndtransformResponse() throws URISyntaxException {
		// GIVEN
		URI uri = new URI("");
		String responseBody = "";
		ResponseEntity<String> response = new ResponseEntity<>(responseBody, HttpStatus.OK);
		BDDMockito.given(repoData.getCategoriesURI()).willReturn(uri);
		BDDMockito.given(restTemplate.getForEntity(uri, String.class)).willReturn(response);
		BDDMockito.given(transformer.convertResponseToList(responseBody, String.class))
				.willReturn(Collections.EMPTY_LIST);
		// WHEN
		underTest.getCategories();
		// THEN
		BDDMockito.then(repoData).should(BDDMockito.times(1)).getCategoriesURI();
		BDDMockito.then(restTemplate).should(BDDMockito.times(1)).getForEntity(uri, String.class);
		BDDMockito.then(transformer).should(BDDMockito.times(1)).convertResponseToList(responseBody, String.class);
	}

	@Test
	public void testGetWisdomsByCategoryShouldCallRepoApiAndtransformResponse() throws URISyntaxException {
		// GIVEN
		String category = "test_category";
		URI uri = new URI("");
		String responseBody = "";
		ResponseEntity<String> response = new ResponseEntity<>(responseBody, HttpStatus.OK);
		BDDMockito.given(repoData.getWisdomsByCategoryURI(category)).willReturn(uri);
		BDDMockito.given(restTemplate.getForEntity(uri, String.class)).willReturn(response);
		BDDMockito.given(transformer.convertResponseToList(responseBody, String.class))
				.willReturn(Collections.EMPTY_LIST);
		// WHEN
		underTest.getWisdomsByCategory(category);
		// THEN
		BDDMockito.then(repoData).should(BDDMockito.times(1)).getWisdomsByCategoryURI(category);
		BDDMockito.then(restTemplate).should(BDDMockito.times(1)).getForEntity(uri, String.class);
		BDDMockito.then(transformer).should(BDDMockito.times(1)).convertResponseToList(responseBody, Wisdom.class);
	}

	@Test
	public void testGetWisdomsByCategoriesAndIdShouldCallRepoApiAndtransformResponse() throws URISyntaxException {
		// GIVEN
		String category = "test_category";
		Long wisdomId = 1L;
		URI uri = new URI("");
		Wisdom wisdom = new Wisdom();
		ResponseEntity<Wisdom> response = new ResponseEntity<>(wisdom, HttpStatus.OK);
		BDDMockito.given(repoData.getWisdomByCategoryAndIdURI(category, wisdomId)).willReturn(uri);
		BDDMockito.given(restTemplate.getForEntity(uri, Wisdom.class)).willReturn(response);
		// WHEN
		underTest.getWisdomByCategoryAndId(category, wisdomId);
		// THEN
		BDDMockito.then(repoData).should(BDDMockito.times(1)).getWisdomByCategoryAndIdURI(category, wisdomId);
		BDDMockito.then(restTemplate).should(BDDMockito.times(1)).getForEntity(uri, Wisdom.class);
	}
}
