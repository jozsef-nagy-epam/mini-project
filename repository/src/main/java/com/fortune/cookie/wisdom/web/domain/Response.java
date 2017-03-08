package com.fortune.cookie.wisdom.web.domain;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {

	private final HttpStatus statusCode;

	@JsonProperty("data")
	private final Object responseBody;

	public Response() {
		this(HttpStatus.OK, null);
	}

	public Response(Object responseBody) {
		this(HttpStatus.OK, responseBody);
	}

	public Response(HttpStatus statusCode, Object responseBody) {
		super();
		this.statusCode = statusCode;
		this.responseBody = responseBody;
	}

	HttpStatus getStatusCode() {
		return statusCode;
	}

	Object getResponseBody() {
		return responseBody;
	}

}
