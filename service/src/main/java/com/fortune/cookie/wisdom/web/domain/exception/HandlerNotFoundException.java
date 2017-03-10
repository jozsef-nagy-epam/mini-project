package com.fortune.cookie.wisdom.web.domain.exception;

public class HandlerNotFoundException extends RuntimeException {

	public HandlerNotFoundException() {
		super();
	}

	public HandlerNotFoundException(String message) {
		super(message);
	}
}
