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
	public void handleError(ClientHttpResponse response) throws IOException {
		throw new RepositoryException(readBody(response.getBody(), response.getStatusCode()), response.getStatusCode());
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
				|| response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
	}

	private String readBody(InputStream body, HttpStatus statusCode) {
		StringBuilder out = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(body));) {
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ResponseConvertException("The resource server responded with " + statusCode, e);
		}
		return out.toString();
	}
}