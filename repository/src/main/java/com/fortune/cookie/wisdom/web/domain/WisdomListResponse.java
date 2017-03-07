package com.fortune.cookie.wisdom.web.domain;

import java.util.List;

public class WisdomListResponse extends AbstractResponse {
	private final List<WisdomResponse> wisdoms;

	public WisdomListResponse(List<WisdomResponse> wisdoms) {
		super();
		this.wisdoms = wisdoms;
	}

	public List<WisdomResponse> getWisdoms() {
		return wisdoms;
	}

}
