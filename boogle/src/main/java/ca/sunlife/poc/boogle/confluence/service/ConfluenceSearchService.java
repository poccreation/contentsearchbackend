
package ca.sunlife.poc.boogle.confluence.service;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClient;

import ca.sunlife.poc.boogle.constants.Constants;
import ca.sunlife.poc.boogle.exception.BoogleException;
import ca.sunlife.poc.boogle.exception.FatalException;
import ca.sunlife.poc.boogle.response.QueryResponse;
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

	@Autowired
	Environment env;

	@Autowired
	IConfluenceAuthService confluenceAuthService;

	@Override
	public List<QueryResponse> searchConfluenceQuery(String query, int page, int pageSize) {

		String accessToken = confluenceAuthService.fetchOAuthToken().getAccess_token();
		if (StringUtils.isEmpty(accessToken)) {
			throw new FatalException(env.getProperty("GENERIC_ERROR_MESSAGE"));
		}
		String formattedUri = MessageFormat.format(searchQueryUri, query, pageSize);
		
		BoogleUtil sunsearchUtil = new BoogleUtil();
		WebClient webClient = sunsearchUtil.getWebClient(baseUrl);
		ConfluenceResponse confluenceResponse = webClient.get().uri(formattedUri).accept(MediaType.APPLICATION_JSON)
				.headers(headers -> headers.setBearerAuth(accessToken)).retrieve()
				.onStatus(HttpStatus::is4xxClientError,
						resp -> Mono.error(new FatalException(env.getProperty("GENERIC_ERROR_MESSAGE"))))
				.onStatus(HttpStatus::is5xxServerError, resp -> Mono.error(new FatalException("GENERIC_ERROR_MESSAGE")))
				.bodyToMono(ConfluenceResponse.class).block();

		if (CollectionUtils.isEmpty(confluenceResponse.getResults())) {
			throw new BoogleException(env.getProperty("NO_RECORD"), Constants.NO_RECORD_FOUND);
		}
		List<QueryResponse> queryResponses = SearchResponseMapper.mapConfluenceResponse(confluenceResponse);

		return queryResponses;

	}

}
