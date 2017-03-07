package com.fortune.cookie.wisdom.service.domain;

public enum Category {
	PUN("pun"), GENERAL("general"), FUTURE("future");

	private final String name;

	private Category(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public static Category getByName(String name) {
		Category result = null;
		for (Category category : values()) {
			if (category.getName().equals(name)) {
				result = category;
			}
		}
		return result;
	}
}
