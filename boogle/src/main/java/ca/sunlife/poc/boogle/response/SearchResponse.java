package ca.sunlife.poc.boogle.response;

import java.util.List;

public class SearchResponse {
	
	List<QueryResponse> queryResponses;
	
	public void setQueryResponses(List<QueryResponse> queryResponses) {
		this.queryResponses = queryResponses;
	}
	
	public List<QueryResponse> getQueryResponses() {
		return queryResponses;
	}

}
