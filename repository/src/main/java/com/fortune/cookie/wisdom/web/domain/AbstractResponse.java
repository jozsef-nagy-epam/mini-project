package com.fortune.cookie.wisdom.web.domain;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbstractResponse {

	@JsonProperty("_links")
	private final Map<String, URI> links;

	public AbstractResponse() {
		links = new HashMap<String, URI>();
	}

	public void addLink(String name, URI uri) {
		links.put(name, uri);
	}
}
