package ca.sunlife.poc.boogle.sharepoint.service;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import ca.sunlife.poc.boogle.exception.FatalException;
import ca.sunlife.poc.boogle.response.QueryResponse;
import ca.sunlife.poc.boogle.response.SearchResponse;
import ca.sunlife.poc.boogle.response.sharepoint.GraphqlResponse;
import ca.sunlife.poc.boogle.response.sharepoint.SharepointResponse;
import ca.sunlife.poc.boogle.sharepoint.request.GraphqlRequest;
import ca.sunlife.poc.boogle.sharepoint.request.Query;
import ca.sunlife.poc.boogle.sharepoint.request.Requests;
import ca.sunlife.poc.boogle.util.BoogleUtil;
import ca.sunlife.poc.boogle.util.SearchResponseMapper;
import reactor.core.publisher.Mono;

@Service
public class SharepointSearchService implements ISharepointSearchService {

	@Value("${sharepoint.base.url}")
	String baseUrl;

	@Value("${sharepoint.search.uri}")
	String searchQueryUri;

	@Value("${sharepoint.accept.header}")
	String sharepointHeader;

	@Value("${sharepoint.graphql.search.url}")
	String graphqlSearchUrl;

	@Value("${sharepoint.graphql.search.uri}")
	String graphSearchUri;

	@Value("#{'${sharepoint.graphql.entitytypes}'.split(',')}")
	private List<String> entityTypes;
	@Value("#{'${sharepoint.graphql.fields}'.split(',')}")
	private List<String> fields;

	@Value("${sharepoint.graphql.region}")
	String region;

	@Autowired
	ISharepointAuthService sharepointAuthService;

	@Autowired
	WebClient webClient;

	@Value("${GENERIC_ERROR_MESSAGE}")
	String msg;

	@Override
	public List<QueryResponse> searchSharepointQuery(String query, int page, int pageSize) {

		String accessToken = sharepointAuthService.fetchOAuthToken();
		if (StringUtils.isEmpty(accessToken)) {
			throw new FatalException(msg);
		}
		String formattedUri = MessageFormat.format(searchQueryUri, query, BoogleUtil.calculateStartRow(page, pageSize),
				pageSize);
		SharepointResponse sharepointResponse = new SharepointResponse();
		String sr = webClient.get().uri(baseUrl + formattedUri).header("Accept", sharepointHeader)
				.headers(headers -> headers.setBearerAuth(accessToken)).retrieve()
				.onStatus(HttpStatus::is4xxClientError, resp -> Mono.error(new FatalException(msg)))
				.onStatus(HttpStatus::is5xxServerError, resp -> Mono.error(new FatalException("GENERIC_ERROR_MESSAGE")))
				.bodyToMono(String.class).block();

		System.out.println(sr);

		List<QueryResponse> queryResponses = SearchResponseMapper.mapSharepointResponse(sharepointResponse);
		return queryResponses;

	}

	@Override
	public SearchResponse searchQueryUsingGraphql(String query, int page, int pageSize) {
		String accessToken = sharepointAuthService.fetchOAuthTokenByClientCredentials().getAccess_token();
		if (StringUtils.isEmpty(accessToken)) {
			throw new FatalException(msg);
		}
		GraphqlResponse graphqlResponse = webClient.post().uri(graphqlSearchUrl + graphSearchUri)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.headers(headers -> headers.setBearerAuth(accessToken))
				.body(Mono.just(setRequest(query, page, pageSize)), GraphqlRequest.class).retrieve()
				.onStatus(HttpStatus::is4xxClientError, resp -> Mono.error(new FatalException(msg)))
				.onStatus(HttpStatus::is5xxServerError, resp -> Mono.error(new FatalException("GENERIC_ERROR_MESSAGE")))
				.bodyToMono(GraphqlResponse.class).block();
		List<QueryResponse> queryResponses = SearchResponseMapper.mapGraphqlResponse(graphqlResponse);
		SearchResponse searchResponse = new SearchResponse();
		searchResponse.setQueryResponses(queryResponses);

		return searchResponse;

	}

	private GraphqlRequest setRequest(String query, int page, int pageSize) {
		GraphqlRequest graphqlRequest = new GraphqlRequest();
		
		// Escape the double quotes
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("'");
		stringBuilder.append(query);
		stringBuilder.append("'");
		
		Requests requests = new Requests();
		requests.setSize(pageSize);
		requests.setFrom(BoogleUtil.calculateStartRow(page, pageSize));
		requests.setEntityTypes(entityTypes);
		requests.setRegion(region);
		requests.setFields(fields);
		Query queryObj = new Query();
		queryObj.setQueryString(stringBuilder.toString());
		requests.setQuery(queryObj);
		graphqlRequest.getRequests().add(requests);

		return graphqlRequest;
	}

}
