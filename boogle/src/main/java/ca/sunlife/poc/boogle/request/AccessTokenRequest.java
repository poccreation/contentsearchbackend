package ca.sunlife.poc.boogle.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessTokenRequest {

	@JsonProperty("grant_type")
	private String grantType;

	@JsonProperty("client_id")
	private String clientId;

	@JsonProperty("client_secret")
	private String clientSecret;


	public AccessTokenRequest(String grantType, String clientId, String clientSecret) {
		this.grantType = grantType;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

}
