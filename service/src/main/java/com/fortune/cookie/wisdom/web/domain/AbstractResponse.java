package com.fortune.cookie.wisdom.web.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.Link;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbstractResponse {

	@JsonProperty("_links")
	private final Set<Link> links;

	public AbstractResponse() {
		links = new HashSet<>();
	}

	public void addLink(Link link) {
		links.add(link);
	}

	public Set<Link> getLinks() {
		return links;
	}

}
