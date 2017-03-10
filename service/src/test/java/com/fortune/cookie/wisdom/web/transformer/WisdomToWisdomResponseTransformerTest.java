package com.fortune.cookie.wisdom.web.transformer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fortune.cookie.wisdom.service.domain.Wisdom;
import com.fortune.cookie.wisdom.web.domain.WisdomResponse;

public class WisdomToWisdomResponseTransformerTest {
	@InjectMocks
	private WisdomToWisdomResponseTransformer underTest;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testConvertShouldConvertProperly() {
		// GIVEN
		Wisdom wisdom = createWisdomSpy();
		// WHEN
		WisdomResponse actual = underTest.convert(wisdom);
		// THEN
		BDDMockito.then(wisdom).should(BDDMockito.times(1)).getId();
		BDDMockito.then(wisdom).should(BDDMockito.times(1)).getText();
		BDDMockito.then(wisdom).should(BDDMockito.times(1)).getCategory();
		assertThat("correctness of id", actual.getId(), equalTo(wisdom.getId()));
		assertThat("correctness of text", actual.getText(), equalTo(wisdom.getText()));
		assertThat("correctness of category", actual.getCategory(), equalTo(wisdom.getCategory()));
	}

	private Wisdom createWisdomSpy() {
		Wisdom wisdom = new Wisdom(1L, "TEST", "general");
		return Mockito.spy(wisdom);
	}
}
