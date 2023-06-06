package ca.sunlife.poc.boogle.response.confluence;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfluenceResponse {

	public ConfluenceResponse() {
		if (null == results) {
			this.results = new ArrayList<>();
		}
	}

	private List<Results> results;
	private long start;
	private long limit;
	@JsonProperty("_links")
	private Links links;

	public List<Results> getResults() {
		return results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}
	
	

}
