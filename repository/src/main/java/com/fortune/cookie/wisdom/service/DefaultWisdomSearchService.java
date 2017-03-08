package com.fortune.cookie.wisdom.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fortune.cookie.wisdom.service.domain.Category;
import com.fortune.cookie.wisdom.service.domain.Wisdom;

@Service
public class DefaultWisdomSearchService implements WisdomSearchService {
	private final List<Wisdom> fakeWisdoms = Arrays.asList(new Wisdom(1L, "first wisdom", Category.GENERAL),
			new Wisdom(2L, "second wisdom", Category.PUN), new Wisdom(3L, "third wisdom", Category.FUTURE),
			new Wisdom(4L, "fourth wisdom", Category.GENERAL));

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return Arrays.asList(Category.values());
	}

	@Override
	public List<Wisdom> getWisdomsByCategory(String category) {
		// TODO Auto-generated method stub
		Category categoryEnum = Category.getByName(category);
		return fakeWisdoms.stream().filter(wisdom -> wisdom.getCategory().equals(categoryEnum))
				.collect(Collectors.toList());
	}

	@Override
	public Wisdom getWisdomByCategoryAndId(String category, Long wisdomId) {
		// TODO Auto-generated method stub
		Category categoryEnum = Category.getByName(category);
		return fakeWisdoms.stream()
				.filter(wisdom -> wisdom.getCategory().equals(categoryEnum) && wisdom.getId().equals(wisdomId))
				.findAny().get();
	}

}
