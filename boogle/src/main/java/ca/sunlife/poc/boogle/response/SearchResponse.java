package ca.sunlife.poc.boogle.response;

import java.util.List;

public class SearchResponse {
	
	private List<QueryResponse> queryResponses;
	private String next;
	public List<QueryResponse> getQueryResponses() {
		return queryResponses;
	}
	public void setQueryResponses(List<QueryResponse> queryResponses) {
		this.queryResponses = queryResponses;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	
	
	
	
	
	
	

}
