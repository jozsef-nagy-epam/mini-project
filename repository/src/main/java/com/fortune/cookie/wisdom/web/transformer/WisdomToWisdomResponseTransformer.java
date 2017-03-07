package com.fortune.cookie.wisdom.web.transformer;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fortune.cookie.wisdom.service.domain.Wisdom;
import com.fortune.cookie.wisdom.web.domain.WisdomResponse;

@Component
public class WisdomToWisdomResponseTransformer implements Converter<Wisdom, WisdomResponse> {

	@Override
	public WisdomResponse convert(Wisdom source) {
		WisdomResponse response = new WisdomResponse(source.getId(), source.getText(), source.getCategory().getName());
		return response;
	}

}
