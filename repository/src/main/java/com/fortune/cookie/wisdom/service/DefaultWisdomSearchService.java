package com.fortune.cookie.wisdom.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fortune.cookie.wisdom.service.domain.Category;
import com.fortune.cookie.wisdom.service.domain.Wisdom;

@Service
public class DefaultWisdomSearchService implements WisdomSearchService {
	private final List<Wisdom> fakeWisdoms = Arrays.asList(new Wisdom(3L, "first wisdom", Category.GENERAL),
			new Wisdom(4L, "second wisdom", Category.PUN), new Wisdom(1L, "second wisdom", Category.FUTURE),
			new Wisdom(2L, "second wisdom", Category.GENERAL));

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return Arrays.asList(Category.values());
	}

	@Override
	public List<Wisdom> getWisdomsByCategory(Category category) {
		// TODO Auto-generated method stub
		return fakeWisdoms.stream().filter(wisdom -> wisdom.getCategory().equals(category))
				.collect(Collectors.toList());
	}

	@Override
	public Wisdom getWisdomById(Category category, Long wisdomId) {
		// TODO Auto-generated method stub
		return fakeWisdoms.stream()
				.filter(wisdom -> wisdom.getCategory().equals(category) && wisdom.getId().equals(wisdomId)).findAny()
				.get();
	}

}
