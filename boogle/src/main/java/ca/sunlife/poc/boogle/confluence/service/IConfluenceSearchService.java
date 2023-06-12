package ca.sunlife.poc.boogle.confluence.service;

import ca.sunlife.poc.boogle.response.SearchResponse;

public interface IConfluenceSearchService {

	public SearchResponse searchConfluenceQuery(String query, int page, int pageSize,String nextLink);

}
