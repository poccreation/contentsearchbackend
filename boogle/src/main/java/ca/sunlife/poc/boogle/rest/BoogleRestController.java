package ca.sunlife.poc.boogle.rest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.sunlife.poc.boogle.confluence.service.IConfluenceSearchService;
import ca.sunlife.poc.boogle.constants.Constants;
import ca.sunlife.poc.boogle.exception.FatalException;
import ca.sunlife.poc.boogle.exception.BoogleException;
import ca.sunlife.poc.boogle.response.ResponseDto;
import ca.sunlife.poc.boogle.response.SearchResponse;
import ca.sunlife.poc.boogle.sharepoint.service.ISharepointSearchService;
import ca.sunlife.poc.boogle.util.BoogleUtil;

@RestController
@RequestMapping(value = "${boogle.base.url}")
public class BoogleRestController {

	@Autowired
	ISharepointSearchService sharepointSearchService;

	@Autowired
	IConfluenceSearchService confluenceSearchService;

	@Autowired
	Environment env;
	

	@GetMapping(value = "${boogle.sharepoint.search.url}")
	public ResponseEntity<ResponseDto<Object>> searchSharepointQuery(
			@RequestParam(required = true, name = "query") String queryText,
			@RequestParam(required = true, name = "page", defaultValue = "1") int page,
			@RequestParam(required = true, name = "size", defaultValue = "25") int size) throws FatalException {

		if (StringUtils.isEmpty(queryText)) {
			return new ResponseEntity<ResponseDto<Object>>(BoogleUtil.mapResponse(Constants.FAILURE,
					Constants.QUERY_BLANK, env.getProperty("NOT_BLANK"), null), HttpStatus.BAD_REQUEST);
		}

		SearchResponse searchResponse = sharepointSearchService.searchSharepointQuery(queryText, page, size);
		ResponseDto<Object> resp = BoogleUtil.mapResponse(Constants.SUCCESSFUL, null, null, searchResponse);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@GetMapping(value = "${boogle.sharepoint.graphql.search.url}")
	public ResponseEntity<ResponseDto<Object>> searchSharepointGraphqlQuery(
			@RequestParam(required = true, name = "query") String queryText,
			@RequestParam(required = true, name = "page", defaultValue = "1") int page,
			@RequestParam(required = true, name = "size", defaultValue = "25") int size) throws FatalException {

		if (StringUtils.isEmpty(queryText)) {
			return new ResponseEntity<ResponseDto<Object>>(BoogleUtil.mapResponse(Constants.FAILURE,
					Constants.QUERY_BLANK, env.getProperty("NOT_BLANK"), null), HttpStatus.BAD_REQUEST);
		}

		SearchResponse searchResponse = sharepointSearchService.searchQueryUsingGraphql(queryText, page, size);
		ResponseDto<Object> resp = BoogleUtil.mapResponse(Constants.SUCCESSFUL, null, null, searchResponse);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

	@GetMapping(value = "${boogle.confluence.search.url}")
	public ResponseEntity<ResponseDto<Object>> searchConfluenceQuery(
			@RequestParam(required = true, name = "query") String queryText,
			@RequestParam(required = true, name = "page", defaultValue = "1") int page,
			@RequestParam(required = true, name = "size", defaultValue = "25") int size)
			throws FatalException, BoogleException {
		if (StringUtils.isEmpty(queryText)) {
			return new ResponseEntity<ResponseDto<Object>>(BoogleUtil.mapResponse(Constants.FAILURE,
					Constants.QUERY_BLANK, env.getProperty("NOT_BLANK"), null), HttpStatus.BAD_REQUEST);
		}
		SearchResponse searchResponse = confluenceSearchService.searchConfluenceQuery(queryText, page, size);
		ResponseDto<Object> resp = BoogleUtil.mapResponse(Constants.SUCCESSFUL, null, null, searchResponse);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}

}
