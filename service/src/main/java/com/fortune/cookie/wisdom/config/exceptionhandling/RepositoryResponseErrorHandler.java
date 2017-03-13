package com.fortune.cookie.wisdom.config.exceptionhandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.fortune.cookie.wisdom.service.domain.exception.RepositoryException;

public class RepositoryResponseErrorHandler implements ResponseErrorHandler {
	private static Logger LOGGER = LoggerFactory.getLogger(RepositoryResponseErrorHandler.class);

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			String responseBody = readBody(response.getBody());
			LOGGER.error("The resource server responded with {}. Message: {}", response.getStatusCode(), responseBody);
			throw new RepositoryException("The service is not available please try again later",
					HttpStatus.SERVICE_UNAVAILABLE);
		} else {
			respondToClientError(response);
		}

	}

	private void respondToClientError(ClientHttpResponse response) throws IOException {
		String responseBody = readBody(response.getBody());
		LOGGER.error("The resource server responded with {}. Message: {}", response.getStatusCode(), responseBody);
		throw new RepositoryException(responseBody, response.getStatusCode());
	}

	private String readBody(InputStream body) {
		StringBuilder out = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(body));) {
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
		} catch (IOException e) {
			LOGGER.error("Error occured during reading response body: {}", e.getMessage());
		}
		return out.toString();
	}
}