package ca.sunlife.poc.boogle.sharepoint.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.microsoft.aad.msal4j.ClientCredentialFactory;
import com.microsoft.aad.msal4j.ClientCredentialParameters;
import com.microsoft.aad.msal4j.ConfidentialClientApplication;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.IClientCredential;
import com.microsoft.aad.msal4j.MsalException;
import com.microsoft.aad.msal4j.SilentParameters;

import ca.sunlife.poc.boogle.exception.FatalException;
import ca.sunlife.poc.boogle.response.AccessTokenResponse;
import reactor.core.publisher.Mono;

@Service
public class SharepointAuthService implements ISharepointAuthService {

	@Value("${GENERIC_ERROR_MESSAGE}")

	private String GENERIC_ERROR_MESSAGE;

	@Value("${sharepoint.client.id}")
	private String SHAREPOINT_CLIENT_ID;

	@Value("${sharepoint.certifcate.path}")
	private String SHAREPOINT_CERTIFICATE_PATH;
	@Value("${sharepoint.certificate.password}")
	private String SHAREPOINT_CERTIFICATE_PASSWORD;
	@Value("${sharepoint.scope}")
	private String SHAREPOINT_SCOPE;
	@Value("${sharepoint.oauth.url}")
	private String SHAREPOINT_OAUTH_URL;
	


	@Value("${sharepoint.oauth.token.uri}")
	String tokenUri;
	@Value("${sharepoint.graphql.grant.type}")
	String grantType;
	@Value("${sharepoint.graphql.client.id}")
	String clientId;
	@Value("${sharepoint.graphql.client.secret}")
	String clientSecret;
	@Value("${sharepoint.graphql.scope}")
	String scope;
	
	@Autowired
	WebClient webClient;
	

	IAuthenticationResult result;
	ConfidentialClientApplication confidentialClientApplication;

	public String fetchOAuthToken() throws FatalException {

		Set<String> scopes = new HashSet<>();
		scopes.add(SHAREPOINT_SCOPE);

		try {
			File file = new File(SHAREPOINT_CERTIFICATE_PATH);
			InputStream pkcs12Certificate = new FileInputStream(file); /* Containing PCKS12-formatted certificate */

			IClientCredential credential = ClientCredentialFactory.createFromCertificate(pkcs12Certificate,
					SHAREPOINT_CERTIFICATE_PASSWORD);

			confidentialClientApplication = ConfidentialClientApplication.builder(SHAREPOINT_CLIENT_ID, credential)
					.authority(SHAREPOINT_OAUTH_URL).build();

			@SuppressWarnings("deprecation")
			SilentParameters silentParameters = SilentParameters.builder(scopes).build();

			// try to acquire token silently. This call will fail since the token cache does
			// not
			// have a token for the application you are requesting an access token for
			result = confidentialClientApplication.acquireTokenSilently(silentParameters).join();
		} catch (Exception ex) {
			if (ex.getCause() instanceof MsalException) {

				ClientCredentialParameters parameters = ClientCredentialParameters.builder(scopes).build();

				// Try to acquire a token. If successful, you should see
				// the token information printed out to console
				result = confidentialClientApplication.acquireToken(parameters).join();
			} else {
				// Handle other exceptions accordingly
				throw new FatalException(GENERIC_ERROR_MESSAGE);
			}
		}
		return result.accessToken();
	}
	
	
	@Override
	public AccessTokenResponse fetchOAuthTokenByClientCredentials() throws FatalException {
		MultiValueMap<String, String> bodyMap= new LinkedMultiValueMap<>();
		bodyMap.add("client_id", clientId);
		bodyMap.add("grant_type", grantType);
		bodyMap.add("client_secret", clientSecret);
		bodyMap.add("scope", scope);
		AccessTokenResponse accessTokenResponse = webClient.post()
			    .uri(SHAREPOINT_OAUTH_URL+tokenUri)
			    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
			    .accept(MediaType.APPLICATION_JSON)
			    .body(BodyInserters.fromFormData(bodyMap))
			    .retrieve()
			    .onStatus(HttpStatus::is4xxClientError, resp -> Mono.error(new FatalException(GENERIC_ERROR_MESSAGE)))
				.onStatus(HttpStatus::is5xxServerError, resp -> Mono.error(new FatalException(GENERIC_ERROR_MESSAGE)))
				.bodyToMono(AccessTokenResponse.class).block();

		return accessTokenResponse;
	}
}
