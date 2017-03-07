package com.fortune.cookie.wisdom.web.transformer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fortune.cookie.wisdom.service.domain.Category;
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
		Assert.assertEquals(wisdom.getId(), actual.getId());
		Assert.assertEquals(wisdom.getText(), actual.getText());
		Assert.assertEquals(wisdom.getCategory().getName(), actual.getCategory());
	}

	private Wisdom createWisdomSpy() {
		Wisdom wisdom = new Wisdom(1L, "TEST", Category.GENERAL);
		return Mockito.spy(wisdom);
	}
}
