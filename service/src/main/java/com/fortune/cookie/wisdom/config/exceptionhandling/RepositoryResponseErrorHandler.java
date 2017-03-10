package com.fortune.cookie.wisdom.config.exceptionhandling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.fortune.cookie.wisdom.service.domain.exception.RepositoryException;
import com.fortune.cookie.wisdom.service.domain.exception.ResponseConvertException;

public class RepositoryResponseErrorHandler implements ResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		throw new RepositoryException(readBody(response), response.getStatusCode());
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	private String readBody(ClientHttpResponse response) {
		StringBuilder out = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.getBody()));) {
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ResponseConvertException("Error occured during reading the content", e);
		}
		return out.toString();
	}
}