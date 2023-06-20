
package ca.sunlife.poc.boogle.confluence.service;

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
import ca.sunlife.poc.boogle.response.confluence.ConfluenceResponse;
import ca.sunlife.poc.boogle.util.BoogleUtil;
import ca.sunlife.poc.boogle.util.SearchResponseMapper;
import reactor.core.publisher.Mono;

@Service
public class ConfluenceSearchService implements IConfluenceSearchService {

	@Value("${confluence.rest.base.url}")
	String baseUrl;

	@Value("${confluence.rest.search.uri}")
	String searchQueryUri;

	@Value("${confluence.cloud.id}")
	String cloudId;

	@Autowired
	IConfluenceAuthService confluenceAuthService;
	
	@Autowired
	WebClient webClient;

	@Value("${GENERIC_ERROR_MESSAGE}")
	String msg;
	
	
	@Override
	public SearchResponse searchConfluenceQuery(String query, int page, int pageSize, String nextLink) {

		
		String accessToken = confluenceAuthService.fetchOAuthToken().getAccess_token();
		if (StringUtils.isEmpty(accessToken)) {
			throw new FatalException(msg);
		}
		String formattedUri = MessageFormat.format(searchQueryUri, query, pageSize);
		if (!StringUtils.isEmpty(nextLink)) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("/ex/confluence/").append(cloudId).append(BoogleUtil.decodeValue(nextLink));
			formattedUri = buffer.toString();
		}

		ConfluenceResponse confluenceResponse = webClient.get().uri(baseUrl+formattedUri).accept(MediaType.APPLICATION_JSON)
				.headers(headers -> headers.setBearerAuth(accessToken)).retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						resp -> Mono.error(new FatalException(msg)))
				.onStatus(HttpStatus::is5xxServerError, resp -> Mono.error(new FatalException(msg)))
				.bodyToMono(ConfluenceResponse.class).block();

		List<QueryResponse> queryResponses = SearchResponseMapper.mapConfluenceResponse(confluenceResponse);
		SearchResponse searchResponse = new SearchResponse();

		searchResponse.setNext(confluenceResponse.getLinks().getNext());
		searchResponse.setQueryResponses(queryResponses);
		return searchResponse;

	}

}
