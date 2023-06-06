package ca.sunlife.poc.boogle.confluence.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import ca.sunlife.poc.boogle.exception.FatalException;
import ca.sunlife.poc.boogle.exception.BoogleException;
import ca.sunlife.poc.boogle.request.AccessTokenRequest;
import ca.sunlife.poc.boogle.response.AccessTokenResponse;
import ca.sunlife.poc.boogle.util.BoogleUtil;
import reactor.core.publisher.Mono;

@Service
public class ConfluenceAuthService implements IConfluenceAuthService {

	@Value("${GENERIC_ERROR_MESSAGE}")

	private String GENERIC_ERROR_MESSAGE;

	@Value("${confluence.oauth.token.base.url}")
	String baseUrl;

	@Value("${confluence.oauth.token.uri}")
	String tokenUri;
	@Value("${confluence.grant.type}")
	String grantType;
	@Value("${confluence.client.id}")
	String clientId;
	@Value("${confluence.client.secret}")
	String clientSecret;

	@Override
	public AccessTokenResponse fetchOAuthToken() throws FatalException, BoogleException {
		BoogleUtil sunsearchUtil = new BoogleUtil();
		WebClient webClient = sunsearchUtil.getWebClient(baseUrl);
		AccessTokenRequest accessTokenRequest = new AccessTokenRequest(grantType, clientId, clientSecret);
		AccessTokenResponse accessTokenResponse = webClient.post().uri(tokenUri).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).body(Mono.just(accessTokenRequest), AccessTokenRequest.class)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, resp -> Mono.error(new FatalException(GENERIC_ERROR_MESSAGE)))
				.onStatus(HttpStatus::is5xxServerError, resp -> Mono.error(new FatalException(GENERIC_ERROR_MESSAGE)))
				.bodyToMono(AccessTokenResponse.class).block();

		return accessTokenResponse;

	}

}
