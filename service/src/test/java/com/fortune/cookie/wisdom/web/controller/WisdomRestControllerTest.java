package com.fortune.cookie.wisdom.web.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.fortune.cookie.wisdom.service.RestTemplateBasedWisdomSearchService;
import com.fortune.cookie.wisdom.service.WisdomSearchService;
import com.fortune.cookie.wisdom.service.domain.Wisdom;
import com.fortune.cookie.wisdom.web.domain.AbstractResponse;
import com.fortune.cookie.wisdom.web.domain.WisdomResponse;
import com.fortune.cookie.wisdom.web.transformer.WisdomToWisdomResponseTransformer;

public class WisdomRestControllerTest {

	private WisdomRestController underTest;
	private WisdomSearchService service;
	private WisdomToWisdomResponseTransformer transformer;

	@Before
	public void setUp() {
		service = Mockito.mock(RestTemplateBasedWisdomSearchService.class);
		transformer = Mockito.mock(WisdomToWisdomResponseTransformer.class);
		underTest = new WisdomRestController(service, transformer);
	}

	@Test
	public void testGetWisdomByCategoryAndIdShouldCallServiceAndTransformerAndLinkFactory() {
		// GIVEN
		String category = "test_category";
		long id = 1L;
		Wisdom wisdom = Wisdom.builder().withId(1L).withText("test_text").withCategory(category).build();
		;
		WisdomResponse wisdomResponse = new WisdomResponse(id, category, "test_text");
		BDDMockito.given(transformer.convert(wisdom)).willReturn(wisdomResponse);
		BDDMockito.given(service.getWisdomByCategoryAndId(BDDMockito.anyString(), BDDMockito.anyLong()))
				.willReturn(wisdom);
		// WHEN
		AbstractResponse response = underTest.getWisdomByCategoryAndId(category, id);
		// THEN
		assertThat("response contains the correct wisdom", response, equalTo(wisdomResponse));
	}
}
