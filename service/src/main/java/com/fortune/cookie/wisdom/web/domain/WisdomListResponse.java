package com.fortune.cookie.wisdom.web.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "category", "wisdoms", "_links" })
public class WisdomListResponse extends AbstractResponse {
	private final String category;
	@JsonProperty("wisdoms")
	private final Set<WisdomResponse> wisdoms;

	public WisdomListResponse(String category, Set<WisdomResponse> wisdoms) {
		this.wisdoms = wisdoms;
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public Set<WisdomResponse> getWisdoms() {
		return wisdoms;
	}

}
