package com.fortune.cookie.wisdom.service.domain;

import java.util.List;

public class WisdomList {
	private List<Wisdom> wisdoms;

	public WisdomList() {
	}

	public WisdomList(List<Wisdom> wisdoms) {
		super();
		this.wisdoms = wisdoms;
	}

	public List<Wisdom> getWisdoms() {
		return wisdoms;
	}

	void setWisdoms(List<Wisdom> wisdoms) {
		this.wisdoms = wisdoms;
	}

}
