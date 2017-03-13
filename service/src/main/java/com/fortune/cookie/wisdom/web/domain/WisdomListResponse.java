package com.fortune.cookie.wisdom.web.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "category", "wisdoms", "_links" })
public class WisdomListResponse extends AbstractResponse {
	private final String category;
	@JsonProperty("wisdoms")
	private final List<WisdomResponse> wisdoms;

	public WisdomListResponse(String category, List<WisdomResponse> wisdoms) {
		this.wisdoms = wisdoms;
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public List<WisdomResponse> getWisdoms() {
		return wisdoms;
	}

}
