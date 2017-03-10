package com.fortune.cookie.wisdom.web.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WisdomListResponse {
	@JsonProperty("wisdoms")
	private final List<WisdomResponse> wisdoms;

	public WisdomListResponse(List<WisdomResponse> wisdoms) {
		super();
		this.wisdoms = wisdoms;
	}

}
