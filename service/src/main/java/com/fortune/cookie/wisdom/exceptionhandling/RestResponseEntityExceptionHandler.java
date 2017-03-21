package com.fortune.cookie.wisdom.exceptionhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fortune.cookie.wisdom.web.domain.exception.HandlerNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private static Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = HandlerNotFoundException.class)
	protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		LOGGER.error("Not mapped url has been called.");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(value = HttpClientErrorException.class)
	protected ResponseEntity<Object> clientEx(RuntimeException ex, WebRequest request) {
		HttpClientErrorException clientException = (HttpClientErrorException) ex;
		String bodyOfResponse = clientException.getResponseBodyAsString();
		LOGGER.error(clientException.getResponseBodyAsString());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), clientException.getStatusCode(), request);
	}

	@ExceptionHandler(value = HttpServerErrorException.class)
	protected ResponseEntity<Object> serverEx(RuntimeException ex, WebRequest request) {
		HttpServerErrorException clientException = (HttpServerErrorException) ex;
		String bodyOfResponse = clientException.getResponseBodyAsString();
		LOGGER.error(clientException.getResponseBodyAsString());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), clientException.getStatusCode(), request);
	}
}