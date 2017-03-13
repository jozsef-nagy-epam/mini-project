package com.fortune.cookie.wisdom.service.domain;

import java.util.Set;

public class WisdomList {
	private Set<Wisdom> wisdoms;

	public WisdomList() {
	}

	public WisdomList(Set<Wisdom> wisdoms) {
		super();
		this.wisdoms = wisdoms;
	}

	public Set<Wisdom> getWisdoms() {
		return wisdoms;
	}

	void setWisdoms(Set<Wisdom> wisdoms) {
		this.wisdoms = wisdoms;
	}

}
