package ca.sunlife.poc.boogle.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.sunlife.poc.boogle.confluence.service.IConfluenceSearchService;
import ca.sunlife.poc.boogle.exception.BoogleException;
import ca.sunlife.poc.boogle.exception.FatalException;
import ca.sunlife.poc.boogle.response.QueryResponse;
import ca.sunlife.poc.boogle.response.SearchResponse;
import ca.sunlife.poc.boogle.sharepoint.service.ISharepointSearchService;

@RestController
@CrossOrigin
@RequestMapping(value = "${boogle.base.url}")
public class BoogleRestController {

	@Autowired
	ISharepointSearchService sharepointSearchService;

	@Autowired
	IConfluenceSearchService confluenceSearchService;

	@Autowired
	Environment env;

	@GetMapping(value = "${boogle.sharepoint.search.url}")
	public ResponseEntity<List<QueryResponse>> searchSharepointQuery(
			@RequestParam(required = true, name = "query") String queryText,
			@RequestParam(required = true, name = "page", defaultValue = "1") int page,
			@RequestParam(required = true, name = "size", defaultValue = "25") int size) throws FatalException {

		List<QueryResponse> queryResponses = sharepointSearchService.searchSharepointQuery(queryText, page, size);
		return new ResponseEntity<>(queryResponses, HttpStatus.OK);
	}

	@GetMapping(value = "${boogle.sharepoint.graphql.search.url}")
	public ResponseEntity<List<QueryResponse>> searchSharepointGraphqlQuery(
			@RequestParam(required = true, name = "query") String queryText,
			@RequestParam(required = true, name = "page", defaultValue = "1") int page,
			@RequestParam(required = true, name = "size", defaultValue = "25") int size) throws FatalException {

		List<QueryResponse> queryResponses = sharepointSearchService.searchQueryUsingGraphql(queryText, page, size);
		return new ResponseEntity<>(queryResponses, HttpStatus.OK);
	}

	@GetMapping(value = "${boogle.confluence.search.url}")
	public ResponseEntity<SearchResponse> searchConfluenceQuery(
			@RequestParam(required = true, name = "query") String queryText,
			@RequestParam(required = true, name = "page", defaultValue = "1") int page,
			@RequestParam(required = true, name = "size", defaultValue = "25") int size,@RequestParam(required = false, name = "nextLink") String nextLink)
			throws FatalException, BoogleException {
		SearchResponse searchResponse = confluenceSearchService.searchConfluenceQuery(queryText, page, size,nextLink);
		return new ResponseEntity<>(searchResponse, HttpStatus.OK);
	}
}
