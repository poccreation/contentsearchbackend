package ca.sunlife.poc.boogle.confluence.service;

import java.util.List;

import ca.sunlife.poc.boogle.response.QueryResponse;

public interface IConfluenceSearchService {

	public List<QueryResponse> searchConfluenceQuery(String query, int page, int pageSize);

}
