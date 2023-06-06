package ca.sunlife.poc.boogle.sharepoint.service;

import java.util.List;

import ca.sunlife.poc.boogle.response.QueryResponse;

public interface ISharepointSearchService {

	public List<QueryResponse> searchSharepointQuery(String query, int page, int pageSize);

	public List<QueryResponse> searchQueryUsingGraphql(String query, int page, int pageSize);

}
