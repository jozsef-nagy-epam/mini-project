package com.fortune.cookie.wisdom.service.domain.exception;

import org.springframework.http.HttpStatus;

public class RepositoryException extends RuntimeException {
	private final HttpStatus statuscode;

	public RepositoryException(String message, HttpStatus statuscode) {
		super(message);
		this.statuscode = statuscode;
	}

	public HttpStatus getStatuscode() {
		return statuscode;
	}

}
