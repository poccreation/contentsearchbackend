package ca.sunlife.poc.boogle.sharepoint.service;

import java.util.List;

import ca.sunlife.poc.boogle.response.QueryResponse;
import ca.sunlife.poc.boogle.response.SearchResponse;

public interface ISharepointSearchService {

	public List<QueryResponse> searchSharepointQuery(String query, int page, int pageSize);

	public SearchResponse searchQueryUsingGraphql(String query, int page, int pageSize);

}
