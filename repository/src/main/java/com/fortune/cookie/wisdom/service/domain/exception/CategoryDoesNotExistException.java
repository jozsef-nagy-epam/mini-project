package com.fortune.cookie.wisdom.service.domain.exception;

public class CategoryDoesNotExistException extends RuntimeException {

	public CategoryDoesNotExistException() {
		super();
	}

	public CategoryDoesNotExistException(String message) {
		super(message);
	}
}
