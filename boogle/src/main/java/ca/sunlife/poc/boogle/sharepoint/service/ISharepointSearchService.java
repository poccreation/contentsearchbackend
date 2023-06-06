package ca.sunlife.poc.boogle.sharepoint.service;

import ca.sunlife.poc.boogle.response.SearchResponse;

public interface ISharepointSearchService {

	public SearchResponse searchSharepointQuery(String query, int page, int pageSize);

	public SearchResponse searchQueryUsingGraphql(String query, int page, int pageSize);

}
