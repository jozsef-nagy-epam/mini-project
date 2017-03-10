package com.fortune.cookie.wisdom.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fortune.cookie.wisdom.service.domain.exception.RepositoryException;
import com.fortune.cookie.wisdom.service.domain.exception.ResponseConvertException;
import com.fortune.cookie.wisdom.web.domain.exception.HandlerNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = RepositoryException.class)
	protected ResponseEntity<Object> handleRepositoryException(RepositoryException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), ex.getStatuscode(), request);
	}

	@ExceptionHandler(value = ResponseConvertException.class)
	protected ResponseEntity<Object> handleNoContent(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NO_CONTENT, request);
	}

	@ExceptionHandler(value = HandlerNotFoundException.class)
	protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}