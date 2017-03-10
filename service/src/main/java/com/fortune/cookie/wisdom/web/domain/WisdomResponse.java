package com.fortune.cookie.wisdom.web.domain;

public class WisdomResponse extends AbstractResponse {
    // Long vs long. Why this? or Why the other?
	private final Long id;
	private final String text;
	// this is repeated in Category response
    // you might want to move it into Abstract Response
	private final String category;

	// What about nulls? @NonNull? Assert not null?
	public WisdomResponse(Long id, String text, String category) {
		this.id = id;
		this.text = text;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public String getCategory() {
		return category;
	}

}
