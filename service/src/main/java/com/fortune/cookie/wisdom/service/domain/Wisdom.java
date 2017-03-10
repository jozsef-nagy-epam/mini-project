package com.fortune.cookie.wisdom.service.domain;

// for the sake of exercise, please add a static inner builder class
public class Wisdom {
	// Lets make this class immutable, please add final to the fields
    // Is there a specific reason for choosing Long over long?
	private Long id;
	private String text;
	private String category;

	// This seems like a bad idea to allow the construction of this object
    // in a way that would allow it to exist in an invalid state
	public Wisdom() {
	}

	// you can also mark constructor arguments as final
	// it is also worth considering how null arguments would be handled
    //   suggest @NonNull annotation (mostly for documenting usage
	public Wisdom(Long id, String text, String category) {
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

	// I don't think there is a need for the setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
