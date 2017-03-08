package com.fortune.cookie.wisdom.service.domain.exception;

public class WisdomDoesNotExistException extends RuntimeException {

	public WisdomDoesNotExistException() {
		super();
	}

	public WisdomDoesNotExistException(String message) {
		super(message);
	}
}
