package ca.sunlife.poc.boogle.sharepoint.service;

import ca.sunlife.poc.boogle.exception.FatalException;
import ca.sunlife.poc.boogle.response.AccessTokenResponse;

public interface ISharepointAuthService {
	
	public String fetchOAuthToken () throws FatalException;
	public AccessTokenResponse fetchOAuthTokenByClientCredentials() throws FatalException;
}
