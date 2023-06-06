package ca.sunlife.poc.boogle.response.confluence;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Space {

	private String name;
	@JsonProperty("_links")
	private Links links;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

}
