package com.fortune.cookie.wisdom.web.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.fortune.cookie.wisdom.service.RestTemplateBasedWisdomSearchService;
import com.fortune.cookie.wisdom.service.WisdomSearchService;
import com.fortune.cookie.wisdom.service.domain.Wisdom;
import com.fortune.cookie.wisdom.web.domain.WisdomListResponse;
import com.fortune.cookie.wisdom.web.domain.WisdomResponse;
import com.fortune.cookie.wisdom.web.transformer.WisdomToWisdomResponseTransformer;

public class WisdomListRestControllerTest {
	private WisdomListRestController underTest;
	private WisdomSearchService service;
	private WisdomToWisdomResponseTransformer transformer;

	private String category = "test_category";
	private Wisdom wisdom = new Wisdom(1L, category, "test_text");
	private WisdomResponse wisdomResponse = new WisdomResponse(1L, category, "test_text");

	@Before
	public void setUp() {
		service = Mockito.mock(RestTemplateBasedWisdomSearchService.class);
		transformer = Mockito.mock(WisdomToWisdomResponseTransformer.class);
		underTest = new WisdomListRestController(service, transformer);
		List<Wisdom> wisdoms = Arrays.asList(wisdom);
		BDDMockito.given(transformer.convert(wisdom)).willReturn(wisdomResponse);
		BDDMockito.given(service.getWisdomsByCategory(BDDMockito.anyString())).willReturn(wisdoms);
	}

	@Test
	public void testGetWisdomsBzCategoriesShouldCallServiceAndTransformerAndLinkFactory() {
		// GIVEN IN SETUP
		// WHEN
		WisdomListResponse response = underTest.getWisdomsByCategories(category);
		// THEN
		assertThat("category is the same as requested", response.getCategory(), equalTo(category));
		assertThat("size of response list", response.getWisdoms().size() == 1);
		assertThat("response type check", response.getWisdoms().get(0), instanceOf(WisdomResponse.class));
		assertThat("response contains the correct wisdom", ((WisdomResponse) response.getWisdoms().get(0)),
				equalTo(wisdomResponse));
	}
}
