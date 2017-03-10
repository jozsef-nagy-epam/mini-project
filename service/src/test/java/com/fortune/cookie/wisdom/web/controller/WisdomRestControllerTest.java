package com.fortune.cookie.wisdom.web.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import com.fortune.cookie.wisdom.service.RestTemplateBasedWisdomSearchService;
import com.fortune.cookie.wisdom.service.WisdomSearchService;
import com.fortune.cookie.wisdom.service.domain.Wisdom;
import com.fortune.cookie.wisdom.web.domain.AbstractResponse;
import com.fortune.cookie.wisdom.web.domain.WisdomResponse;
import com.fortune.cookie.wisdom.web.domain.factory.ResponseLinkFactory;
import com.fortune.cookie.wisdom.web.transformer.WisdomToWisdomResponseTransformer;

public class WisdomRestControllerTest {

	private WisdomRestController underTest;
	private WisdomSearchService service;
	private WisdomToWisdomResponseTransformer transformer;
	private ResponseLinkFactory linkFactory;

	@Before
	public void setUp() {
		service = Mockito.mock(RestTemplateBasedWisdomSearchService.class);
		transformer = Mockito.mock(WisdomToWisdomResponseTransformer.class);
		linkFactory = Mockito.mock(ResponseLinkFactory.class);
		underTest = new WisdomRestController(service, transformer);
	}

	// @Test
	// public void
	// testGetCategoriesShouldCallServiceAndTransformerAndLinkFactory() {
	// // GIVEN
	// String category = "Test_category";
	// List<String> categories = Arrays.asList(category);
	// BDDMockito.given(linkFactory.addLinkToResponse(BDDMockito.any(CategoryResponse.class)))
	// .willReturn(new CategoryResponse(category));
	// BDDMockito.given(service.getCategories()).willReturn(categories);
	// // WHEN
	// List<AbstractResponse> response = underTest.getCategories();
	// // THEN
	// assertThat("size of response list", response.size() == 1);
	// assertThat("response type check", response.get(0),
	// instanceOf(CategoryResponse.class));
	// assertThat("response contains the correct category", ((CategoryResponse)
	// response.get(0)).getCategory(),
	// equalTo(category));
	// }

	// @Test
	// public void
	// testGetWisdomsBzCategoriesShouldCallServiceAndTransformerAndLinkFactory()
	// {
	// // GIVEN
	// Wisdom wisdom = new Wisdom(1L, "test_category", "test_text");
	// List<Wisdom> wisdoms = Arrays.asList(wisdom);
	// WisdomResponse wisdomResponse = new WisdomResponse(1L, "test_category",
	// "test_text");
	// BDDMockito.given(transformer.convert(wisdom)).willReturn(wisdomResponse);
	// BDDMockito.given(linkFactory.addLinkToResponse(BDDMockito.any(WisdomResponse.class)))
	// .willReturn(wisdomResponse);
	// BDDMockito.given(service.getWisdomsByCategory(BDDMockito.anyString())).willReturn(wisdoms);
	// // WHEN
	// List<AbstractResponse> response =
	// underTest.getWisdomsByCategories("test_category");
	// // THEN
	// assertThat("size of response list", response.size() == 1);
	// assertThat("response type check", response.get(0),
	// instanceOf(WisdomResponse.class));
	// assertThat("response contains the correct wisdom", ((WisdomResponse)
	// response.get(0)), equalTo(wisdomResponse));
	// }

	@Test
	public void testGetWisdomByCategoryAndIdShouldCallServiceAndTransformerAndLinkFactory() {
		// GIVEN
		String category = "test_category";
		long id = 1L;
		Wisdom wisdom = new Wisdom(id, category, "test_text");
		WisdomResponse wisdomResponse = new WisdomResponse(id, category, "test_text");
		BDDMockito.given(transformer.convert(wisdom)).willReturn(wisdomResponse);
		BDDMockito.given(linkFactory.addLinkToResponse(BDDMockito.any(WisdomResponse.class)))
				.willReturn(wisdomResponse);
		BDDMockito.given(service.getWisdomByCategoryAndId(BDDMockito.anyString(), BDDMockito.anyLong()))
				.willReturn(wisdom);
		// WHEN
		AbstractResponse response = underTest.getWisdomByCategoryAndId(category, id);
		// THEN
		assertThat("response type check", response, instanceOf(WisdomResponse.class));
		assertThat("response contains the correct wisdom", ((WisdomResponse) response), equalTo(wisdomResponse));
	}
}
