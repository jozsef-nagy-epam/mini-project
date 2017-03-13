package com.fortune.cookie.wisdom.exceptionhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fortune.cookie.wisdom.service.domain.exception.RepositoryException;
import com.fortune.cookie.wisdom.web.domain.exception.HandlerNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private static Logger LOGGER = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

	@ExceptionHandler(value = RepositoryException.class)
	protected ResponseEntity<Object> handleRepositoryException(RepositoryException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		LOGGER.error("Repository exception occured. Message: {} ", ex.getMessage(), ex.getCause());
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), ex.getStatuscode(), request);
	}

	@ExceptionHandler(value = HandlerNotFoundException.class)
	protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		LOGGER.error("Not mapped url has been called.");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}