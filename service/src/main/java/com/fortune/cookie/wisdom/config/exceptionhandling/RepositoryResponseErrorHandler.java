package com.fortune.cookie.wisdom.config.exceptionhandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.fortune.cookie.wisdom.service.domain.exception.RepositoryException;
import com.fortune.cookie.wisdom.service.domain.exception.ResponseConvertException;

public class RepositoryResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
			throw new RepositoryException("The service is not available please try again later",
					HttpStatus.SERVICE_UNAVAILABLE);
		} else {
			respondToClientError(response);
		}

	}

	private void respondToClientError(ClientHttpResponse response) throws IOException {
		String errorMessage;
		try {
			errorMessage = readBody(response.getBody());
		} catch (ResponseConvertException e) {
			throw new RepositoryException("The service is not available please try again later",
					HttpStatus.SERVICE_UNAVAILABLE);
		}
		throw new RepositoryException(errorMessage, response.getStatusCode());
	}

	private String readBody(InputStream body) {
		StringBuilder out = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(body));) {
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ResponseConvertException("Error during reading error from body ", e);
		}
		return out.toString();
	}
}